package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/novias/")
public class noviasController {
    @RequestMapping("/")
    private String novias(Model model, HttpSession session)
    {
        if (session.getAttribute("usuario") != null) {
            String usuario = (String) session.getAttribute("usuario");
            model.addAttribute("mensaje_ini", "Hola " + usuario + "!");
        } else {
            model.addAttribute("mensaje_ini", "Iniciar Sesion");
        }
        return "novias";
    }
}
