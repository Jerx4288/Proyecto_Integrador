package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/productodetalle")
public class productoDetalleController 
{
    @RequestMapping("/")
    public String detalle(@RequestParam(name = "id", required = false) String id, Model model) {
        
        model.addAttribute("productoId", id);
        return "producto_detalle"; 

    }
}