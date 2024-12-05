package com.integrador.proyecto_integrador.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.integrador.proyecto_integrador.model.Administrador;
import com.integrador.proyecto_integrador.model.Cliente;
import com.integrador.proyecto_integrador.model.service.IClienteService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class loginController {

    @Autowired
    private IClienteService clienteService;

    @RequestMapping("/")
    private String inicio (Model model)
    {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "loginyregitser";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(Cliente cliente) {
        System.out.println("Datos del cliente: " + cliente);
        String resultado = clienteService.guardarCliente(cliente);
        System.out.println(resultado);
   
        return "redirect:/login/";
    }

    @RequestMapping(value = "/iniciar", method = RequestMethod.POST)
    public String iniciarSesion(
        @RequestParam("dni") String dni,
        @RequestParam("password") String password,
        @RequestParam("rol") String rol,
        RedirectAttributes redirectAttributes,
        Model model,
        HttpSession session) {
    
        System.out.println("Datos del cliente: " + dni);
    
        if ("admin".equals(rol)) {
            System.out.println("Es admin");
            Optional<Administrador> administradorOpt = clienteService.iniciarSesionAdmin(dni, password);

        if (administradorOpt.isPresent()) {
            Administrador administrador = administradorOpt.get();
            session.setAttribute("usuario", administrador);  // Guarda el objeto completo en la sesión
            session.setAttribute("rol",rol); // Ajusta según el rol

            String mensajeBienvenida = "Hola " + administrador.getNombre_a() + " !";
            model.addAttribute("mensaje_ini", mensajeBienvenida);
            return "redirect:/admin/";
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Dni o Contraseña inválido");
            return "redirect:/login/?error=true";
        }
        
    } else {
        System.out.println("Es usuario");
        Optional<Cliente> clienteOpt = clienteService.iniciarSesion(dni, password);
        
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            session.setAttribute("usuario", cliente);  // Guarda el objeto completo en la sesión
            session.setAttribute("rol", cliente.getRol());

            String mensajeBienvenida = "Hola " + cliente.getNombre() + " !";
            model.addAttribute("mensaje_ini", mensajeBienvenida);
            return "MenuPrincipal";
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Dni o Contraseña inválido");
            return "redirect:/login/?error=true";
        }
    }
}
     
    @GetMapping("/logout")
     public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "redirect:/Pagina_principal";
    }


    


}
