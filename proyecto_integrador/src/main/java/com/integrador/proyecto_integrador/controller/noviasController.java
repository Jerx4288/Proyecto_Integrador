package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.integrador.proyecto_integrador.model.Administrador;
import com.integrador.proyecto_integrador.model.Cliente;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/novias/")
public class noviasController {
    @RequestMapping("/")
    private String novias(Model model, HttpSession session)
    {
        String mensajeBienvenida;
        Object usuario = session.getAttribute("usuario");

        if (usuario instanceof Administrador) {
            Administrador administrador = (Administrador) usuario;
            mensajeBienvenida = "Hola " + administrador.getNombre_a() + "!";
        } else if (usuario instanceof Cliente) {
            Cliente cliente = (Cliente) usuario;
            mensajeBienvenida = "Hola " + cliente.getNombre() + "!";
        } else {
            mensajeBienvenida = "¡Hola!";
        }
        model.addAttribute("mensaje_ini", mensajeBienvenida);
        return "novias";
    }
}
