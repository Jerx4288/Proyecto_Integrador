package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/productodetalle")
public class productoDetalleController 
{
    @RequestMapping("/")
    public String detalle(@RequestParam(name = "id", required = false) String id, Model model, HttpSession session) {
       
        model.addAttribute("productoId", id);
        if (session.getAttribute("usuario") != null) {
            String usuario = (String) session.getAttribute("usuario");
            model.addAttribute("mensaje_ini", "Hola " + usuario + "!");
        } else {
            model.addAttribute("mensaje_ini", "Iniciar Sesion");
        }
        return "producto_detalle"; 

    }
}