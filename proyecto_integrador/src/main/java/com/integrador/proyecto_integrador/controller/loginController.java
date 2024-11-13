package com.integrador.proyecto_integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String iniciarSesion(@RequestParam("dni") String dni, @RequestParam("password") String password, @RequestParam("rol") String rol, RedirectAttributes redirectAttributes, 
    Model model, HttpSession session) {
        System.out.println("Datos del cliente: " + dni);
        String nombreUsuario;
        String rolUsuario = rol;
        String error;
        String e2;
        if ("admin".equals(rol)) {
            System.out.println("Es admin");
           
            e2 = clienteService.iniciarSesionAdmin(dni, password);
            if (e2.isEmpty()) {
                e2 = "Dni o Contraseña invalido";
                redirectAttributes.addFlashAttribute("mensaje", e2);
                
                return "redirect:/login/?error=true";
            } else {
                e2 = "Hola " + clienteService.iniciarSesionAdmin(dni, password) + " !";
                nombreUsuario = clienteService.iniciarSesionAdmin(dni, password);
                session.setAttribute("usuario", nombreUsuario);
                session.setAttribute("rol", rolUsuario);
                session.setAttribute("dni", dni);
                System.out.println(dni);
                System.out.println("Usuario de sesión: " + session.getAttribute("usuario")); 
                model.addAttribute("mensaje_ini", e2);
                
                return "MenuPrincipal";
            }
        } else {
            System.out.println("Es usuario");
            error = clienteService.iniciarSesion(dni, password);            
            if (error.isEmpty()) {
                error = "Dni o Contraseña invalido";
                redirectAttributes.addFlashAttribute("mensaje", error);
                return "redirect:/login/?error=true";
            } else {
                nombreUsuario = clienteService.iniciarSesion(dni, password); 
                error = "Hola " + clienteService.iniciarSesion(dni, password) + " !";
                session.setAttribute("usuario", nombreUsuario);
                session.setAttribute("rol", rolUsuario);
                session.setAttribute("dni", dni);
                System.out.println("Usuario de sesión: " + session.getAttribute("usuario")); 
                model.addAttribute("mensaje_ini", error);
                return "MenuPrincipal";
            }
        }
    }
     
    @GetMapping("/logout")
     public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "redirect:/Pagina_principal";
    }

    @RequestMapping("/cuenta")
    public String rediCuenta()
    {
        return "redirect:/usuario/";
    }
    


}
