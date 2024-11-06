package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/nosotros")
public class nosotrosController {
    @RequestMapping("/")
    private String nosotros (Model model, HttpSession session)
    {
        if (session.getAttribute("usuario") != null) {
            String usuario = (String) session.getAttribute("usuario");
            model.addAttribute("mensaje_ini", "Hola " + usuario + "!");
        } else {
            model.addAttribute("mensaje_ini", "Iniciar Sesion");
        }
        return "nosotros"; 
    }
}
