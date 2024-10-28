package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nosotros")
public class nosotrosController {
    @RequestMapping("/")
    private String nosotros ()
    {
        return "Nosotros";
    }
}
