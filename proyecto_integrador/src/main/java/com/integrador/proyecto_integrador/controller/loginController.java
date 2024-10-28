package com.integrador.proyecto_integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.integrador.proyecto_integrador.model.Cliente;
import com.integrador.proyecto_integrador.model.service.IClienteService;

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
    public String iniciarSesion(@RequestParam("dni") String dni, @RequestParam("password") String password, @RequestParam("rol") String rol,RedirectAttributes redirectAttributes) {
        System.out.println("Datos del cliente: " + dni); 
        String resultado;

        if ("admin".equals(rol)) {
            resultado = clienteService.iniciarSesionAdmin(dni, password);
            System.out.println("Es admin");
            if (resultado.startsWith("Inicio de sesión exitoso")) {
                return "administrador";
            } else {
                redirectAttributes.addFlashAttribute("mensaje", resultado);
                return "redirect:/login/?error=true";
            }
        } else {
            resultado = clienteService.iniciarSesion(dni, password);
            System.out.println("Es usuario");
            if (resultado.startsWith("Inicio de sesión exitoso")) {
                return "usuario";
            } else {
                redirectAttributes.addFlashAttribute("mensaje", resultado);
                return "redirect:/login/?error=true";
            }
        }
    
        
        
    }


}
