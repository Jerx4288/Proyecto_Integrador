package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/novias/")
public class noviasController {
    @RequestMapping("/")
    private String novias()
    {
        return "novias";
    }
}
