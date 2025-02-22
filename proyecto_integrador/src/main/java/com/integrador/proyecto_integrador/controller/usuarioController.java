package com.integrador.proyecto_integrador.controller;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.proyecto_integrador.model.Boleta;
import com.integrador.proyecto_integrador.model.Citas;
import com.integrador.proyecto_integrador.model.Cliente;
import com.integrador.proyecto_integrador.model.service.IBoletaService;
import com.integrador.proyecto_integrador.model.service.ICitaService;
import com.integrador.proyecto_integrador.model.service.IClienteService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.FileSystemResource;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class usuarioController {

    @Autowired
    IClienteService clienteService;

    @Autowired
    ICitaService citaService;

    @Autowired
    IBoletaService boletaService;

    @RequestMapping("/")
    private String inicio (Model model, HttpSession session)
    {
        String dni;
        Object usuario = session.getAttribute("usuario");
        Cliente cliente = (Cliente) usuario;
        dni = cliente.getDni();
        System.out.println(dni);

        Cliente cliente2 = clienteService.buscarCliente(dni);
        model.addAttribute("cliente", cliente2);
        System.out.println(cliente2);
         
        return "usuario";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    private String guardar(Cliente cliente, RedirectAttributes redirectAttributes)
    {
        String rpta = clienteService.guardarCliente(cliente);
        redirectAttributes.addFlashAttribute("mensaje",rpta);
        return "redirect:/usuario/";
    }


    @RequestMapping("/historial")
    private String historial(
            @RequestParam(value = "mes", required = false) Integer mes,
            @RequestParam(value = "anio", required = false) Integer anio,
            Model model, HttpSession session) throws JsonProcessingException {
    
        // Verificar si el usuario está en sesión
        Object usuario = session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login/";
        }
        Cliente cliente = (Cliente) usuario;
        String dni = cliente.getDni();
    
        // Mapa para convertir los números de mes a nombre de mes
        String[] meses = new DateFormatSymbols().getMonths();
        
        // Crear un mapa para contar las boletas por mes
        Map<String, Long> boletasPorMes = new LinkedHashMap<>();
        for (int i = 0; i < 12; i++) {
            boletasPorMes.put(meses[i], 0L); // Usamos el nombre del mes
        }
    
        // Filtrar boletas según el mes y año proporcionados
        List<Boleta> boletas = (mes != null && anio != null)
            ? boletaService.filtrarPorMesYAnio(dni, mes, anio)
            : boletaService.cargarCategoriasFiltradas(dni);
    
        // Procesar las boletas y agrupar por mes
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        Map<String, Long> boletasAgrupadas = boletas.stream()
                .collect(Collectors.groupingBy(
                    boleta -> boleta.getFecha_boleta().format(formatter),
                    Collectors.counting()
                ));
    
        // Asegurarse de que los meses sin datos se mantengan con 0
        boletasAgrupadas.forEach((fecha, count) -> {
            String mesNombre = meses[Integer.parseInt(fecha.split("-")[1]) - 1]; // Convertimos el número a nombre
            boletasPorMes.put(mesNombre, count);
        });
    
        List<String> labels = new ArrayList<>(boletasPorMes.keySet());
        List<Long> values = new ArrayList<>(boletasPorMes.values());
        
        // Convertir las listas a JSON
        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("labelsJson", mapper.writeValueAsString(labels));
        model.addAttribute("valuesJson", mapper.writeValueAsString(values));
        model.addAttribute("listaBoleta", boletas);
        model.addAttribute("totalBoletasMes", boletas.size()); // Enviar el total al modelo
        model.addAttribute("años", IntStream.range(2020, 2025).boxed().collect(Collectors.toList()));
    
        return "usuarioHistorialCompras";
    }

    @RequestMapping("/citas")
    private String citas (@RequestParam(value = "mes", required = false) Integer mes,
    @RequestParam(value = "anio", required = false) Integer anio,
    Model model, HttpSession session) throws JsonProcessingException 
    {
        // Verificar si el usuario está en sesión
        Object usuario = session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login/";
        }
        Cliente cliente = (Cliente) usuario;
        String dni = cliente.getDni();
    
        // Mapa para convertir los números de mes a nombre de mes
        String[] meses = new DateFormatSymbols().getMonths();
        
        // Crear un mapa para contar las boletas por mes
        Map<String, Long> citasPorMes = new LinkedHashMap<>();
        for (int i = 0; i < 12; i++) {
            citasPorMes.put(meses[i], 0L); // Usamos el nombre del mes
        }
    
        // Filtrar boletas según el mes y año proporcionados
        List<Citas> citas = (mes != null && anio != null)
            ? citaService.filtrarPorMesYAnio(dni, mes, anio)
            : citaService.cargarCitasFiltradas(dni);
    
        // Procesar las boletas y agrupar por mes
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        Map<String, Long> citasAgrupadas = citas.stream()
                .collect(Collectors.groupingBy(
                        cita -> cita.getFecha_c().format(formatter),
                        Collectors.counting()
                ));

    
        // Asegurarse de que los meses sin datos se mantengan con 0
        citasAgrupadas.forEach((fecha, count) -> {
            String mesNombre = meses[Integer.parseInt(fecha.split("-")[1]) - 1]; // Convertimos el número a nombre
            citasPorMes.put(mesNombre, count);
        });
    
        List<String> labels = new ArrayList<>(citasPorMes.keySet());
        List<Long> values = new ArrayList<>(citasPorMes.values());
        
        // Convertir las listas a JSON
        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("labelsJson", mapper.writeValueAsString(labels));
        model.addAttribute("valuesJson", mapper.writeValueAsString(values));
        model.addAttribute("listaCitas", citas);
        model.addAttribute("totalCitasMes", citas.size()); // Enviar el total al modelo
        model.addAttribute("años", IntStream.range(2020, 2025).boxed().collect(Collectors.toList()));
        return "usuarioCitas";
    }

    @RequestMapping("/editar")
    private String editar(Model model, HttpSession session)
    {
        String dni;
        Object usuario = session.getAttribute("usuario");
        Cliente cliente = (Cliente) usuario;
        dni = cliente.getDni();
        System.out.println(dni);

        Cliente cliente2 = clienteService.buscarCliente(dni);
        model.addAttribute("cliente", cliente2);
        System.out.println(cliente2);
         
        return "usuarioPassword";
    }


    @Controller
    public class ImagenController {

    @RequestMapping("/uploads/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            // Ruta a la carpeta de imágenes
            Path path = Paths.get("C:/Users/mbjhe/OneDrive/Desktop/UTP/PROYECTO_FINAL_INTEGRADOR/proyecto_integrador/uploads/" + filename);
            Resource resource = new FileSystemResource(path);
            
            
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) 
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

}

