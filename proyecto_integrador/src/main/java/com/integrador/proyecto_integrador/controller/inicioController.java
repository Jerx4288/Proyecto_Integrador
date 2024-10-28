package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class inicioController {
    
    @GetMapping("/")
    public String redirectToPaginaPrincipal() {
        return "redirect:/Pagina_principal";
    }
    @RequestMapping("/Pagina_principal")
    private String Inicio ()
    {
        return "MenuPrincipal.html";
    }
}
