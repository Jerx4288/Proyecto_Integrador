package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/productodetalle")
public class proDetController {

    @RequestMapping("/")
    public String inicio(@RequestParam(value = "id", required = false) String productoId, Model model, HttpSession session) {
        if (session.getAttribute("usuario") != null) {
            String usuario = (String) session.getAttribute("usuario");
            String dni = (String) session.getAttribute("dni");
            System.out.println(dni);
            model.addAttribute("mensaje_ini", "Hola " + usuario + "!");
            if (productoId == null) 
            {
                return "tienda";
            }else
            {
                System.out.println("Jalo bien id " + productoId );
                return "producto_detalle"; // nombre de la vista Thymeleaf
            }
            
        } else {
            model.addAttribute("mensaje_ini", "Iniciar Sesion");
        }
        System.out.println("Entro al controlador");
        return "producto_detalle"; // nombre de la vista Thymeleaf
    }
}
