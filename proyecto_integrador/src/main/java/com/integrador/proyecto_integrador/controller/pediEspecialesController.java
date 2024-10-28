package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos_especiales/")
public class pediEspecialesController {
    @RequestMapping("/")
    private String inicio ()
    {
        return "personalizada";
    }
}
