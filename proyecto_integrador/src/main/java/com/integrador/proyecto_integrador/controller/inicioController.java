package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class inicioController {
    
    @GetMapping("/")
    public String redirectToPaginaPrincipal() {
        return "redirect:/Pagina_principal";
    }
    @RequestMapping("/Pagina_principal")
    public String Inicio(Model model, HttpSession session) {
        if (session.getAttribute("usuario") != null) {
            String usuario = (String) session.getAttribute("usuario");
            String rol = (String) session.getAttribute("rol");  // Obtener el rol de la sesión
            model.addAttribute("mensaje_ini", "Hola " + usuario + "!");
            model.addAttribute("rol", rol);  // Pasar el rol al modelo
        } else {
            model.addAttribute("mensaje_ini", "Iniciar Sesion");
        }
        return "MenuPrincipal"; 
    }
    

    }
