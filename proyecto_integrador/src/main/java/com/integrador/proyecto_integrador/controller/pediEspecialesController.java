package com.integrador.proyecto_integrador.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.integrador.proyecto_integrador.model.Administrador;
import com.integrador.proyecto_integrador.model.Citas;
import com.integrador.proyecto_integrador.model.Cliente;
import com.integrador.proyecto_integrador.model.service.ICitaService;
import com.integrador.proyecto_integrador.model.service.IUploadFileService;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pedidos_especiales")
public class pediEspecialesController {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ICitaService citaService;

    

    @Autowired
    private IUploadFileService uploadFileService;

    @RequestMapping("/")
    private String inicio (Model model, HttpSession session)
    {
        String mensajeBienvenida;
        Object usuario = session.getAttribute("usuario");

        Citas nuevaCita = new Citas();

        if (usuario instanceof Administrador) {
            Administrador administrador = (Administrador) usuario;
            mensajeBienvenida = "Hola " + administrador.getNombre_a() + "!";
        } else if (usuario instanceof Cliente) {
            Cliente cliente = (Cliente) usuario;
            mensajeBienvenida = "Hola " + cliente.getNombre() + "!";
            nuevaCita.setNombre_c(cliente.getNombre()); 
            nuevaCita.setCorreo_c(cliente.getCorreo());
        } else if (usuario != null) {
            // Si 'usuario' es un String o cualquier otro tipo
            mensajeBienvenida = "Hola " + usuario.toString() + "!";
        } else {
            // Caso donde el usuario no está logueado
            mensajeBienvenida = "Iniciar Sesion";
        }

        model.addAttribute("mensaje_ini", mensajeBienvenida);
        model.addAttribute("citas", nuevaCita);

        return "personalizada";
    }
    @SuppressWarnings("null")
    @PostMapping("/guardar")
    public String guardar( Citas citas, BindingResult result, RedirectAttributes flash, 
         RedirectAttributes redirectAttributes, HttpSession session, @RequestParam("imagen_c") MultipartFile imagen, 
         Model model, SessionStatus status) throws IOException {
    
        System.out.println("Entro al controlador guardar");
    
        // Verificar que la cita se está recibiendo correctamente
        System.out.println("Citas objeto recibido: " + citas);
        System.out.println("Nombre: " + citas.getNombre_c());
        System.out.println("Correo: " + citas.getCorreo_c());
        System.out.println("Celular: " + citas.getCelular_c());
        System.out.println("Fecha: " + citas.getFecha_c());
        System.out.println("Hora: " + citas.getHora_c());
        
        // Verificar el archivo recibido
        if (imagen != null && !imagen.isEmpty()) {
            System.out.println("Archivo recibido: " + imagen.getOriginalFilename());
        } else {
            System.out.println("No se recibió ningún archivo.");
        }
    
        // Obtener el usuario de la sesión
        Object usuario = session.getAttribute("usuario");
        if (usuario instanceof Cliente) {
            Cliente cliente = (Cliente) usuario;
            citas.setCliente(cliente);
    
            if (result.hasErrors()) {
                System.out.println("Errores en el formulario: " + result.getFieldError());
                return "redirect:/pedidos_especiales/";
            } else {
                // Verificar si el archivo no está vacío y luego procesarlo
                if (!imagen.isEmpty()) {
                    System.out.println("Archivo recibido: " + imagen.getOriginalFilename());
    
                    if (citas != null && citas.getImagen_c_ruta() != null && citas.getImagen_c_ruta().length() > 0) {
                        // Eliminar la imagen previa si existe
                        uploadFileService.delete(citas.getImagen_c_ruta());
                    }
    
                    // Procesar el archivo y guardar la nueva imagen
                    String uniqueFileName = uploadFileService.copy(imagen); // Aquí guardas el archivo en el sistema
                    citas.setImagen_c_ruta(uniqueFileName); // Asignar la ruta o nombre del archivo a imagen_c
    
                    System.out.println("Imagen guardada con nombre: " + uniqueFileName);
                } else {
                    System.out.println("No se recibió ningún archivo.");
                }
    
                // Guardar la cita en la base de datos
                citaService.guardarCita(citas);
                try {
                    enviarResumenCitaPorCorreo(citas.getCorreo_c(), citas, imagen);
                    flash.addFlashAttribute("mensaje", "Cita agendada y correo enviado con éxito!");
                } catch (MessagingException | IOException e) {
                    flash.addFlashAttribute("mensaje", "Hubo un error al enviar el correo: " + e.getMessage());
                }
            }
            return "redirect:/pedidos_especiales/";
        } else {
            // Si no hay un cliente en sesión, redirige al login o muestra un mensaje de error
            redirectAttributes.addFlashAttribute("mensaje", "Debe iniciar sesión como cliente para registrar una cita.");
            return "redirect:/login/"; // Cambiar según la ruta de tu página de login
        }
    }
    
    @SuppressWarnings("null")
    public void enviarResumenCitaPorCorreo(String correoDestino, Citas cita, MultipartFile imagen) throws MessagingException, IOException {
        // Crear el mensaje Mime
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true); // true para multipart (adjuntos)
    
        helper.setTo(correoDestino);
        helper.setSubject("Resumen de tu cita - Pabluki's Bakery");
    
        // Crear contenido HTML
        String contenidoHtml = 
                "<html>" +
                "<body style=\"font-family: Arial, sans-serif; line-height: 1.5; color: #333;\">" +
                "    <h2 style=\"text-align: center; color: #f4bdc9;\">Gracias por agendar tu cita en Pabluki's Bakery!</h2>" +
                "    <div style=\"text-align: center; margin-bottom: 20px;\">" +
                "        <img src=\"cid:logo\" alt=\"Pabluki's Bakery\" style=\"width: 200px; border-radius: 48%;\" />" +
                "    </div>" +
                "    <p style=\"text-align: center; margin-top: 20px;\">Estimado cliente,</p>" +
                "    <p style=\"text-align: center; margin-top: 20px;\">Adjunto encontrarás el resumen de tu cita con los siguientes detalles:</p>" +
                "    <ul style=\"color: #333;\">" +
                "        <li><strong>Nombre:</strong> " + cita.getNombre_c() + "</li>" +
                "        <li><strong>Correo:</strong> " + cita.getCorreo_c() + "</li>" +
                "        <li><strong>Celular:</strong> " + cita.getCelular_c() + "</li>" +
                "        <li><strong>Fecha:</strong> " + cita.getFecha_c() + "</li>" +
                "        <li><strong>Hora:</strong> " + cita.getHora_c() + "</li>" +
                "    </ul>" +
                "    <p style=\"text-align: center; margin-top: 20px;\">¡Esperamos verte pronto!</p>" +
                "</body>" +
                "</html>";
    
        helper.setText(contenidoHtml, true); // true para contenido HTML
    
        // Cargar el logotipo desde el classpath
        ClassPathResource logoResource = new ClassPathResource("static/img/logo2.jpg");
        try (InputStream logoStream = logoResource.getInputStream()) {
            DataSource logoDataSource = new ByteArrayDataSource(logoStream, "image/jpeg");
            helper.addInline("logo", logoDataSource); // Inline attachment
        }
    
        if (imagen != null && !imagen.isEmpty()) {
            // Adjuntar la imagen al correo
            helper.addAttachment(imagen.getOriginalFilename(), imagen);
            System.out.println("Imagen adjuntada: " + imagen.getOriginalFilename());
        }
        // Enviar el correo
        javaMailSender.send(mimeMessage);
    
        System.out.println("Correo enviado a: " + correoDestino);
    }


    

}
