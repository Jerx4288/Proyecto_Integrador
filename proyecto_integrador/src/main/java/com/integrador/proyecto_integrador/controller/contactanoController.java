package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contactanos")
public class contactanoController {
    @RequestMapping("/")
    private String inicio()
    {
        return "contactanos";
    }
}
