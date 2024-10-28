package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tienda")
public class tiendaController {
    
    @RequestMapping("/")
    private String inicio ()
    {
        return "Tienda";
    }
}
