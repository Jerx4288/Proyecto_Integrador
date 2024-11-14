package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.integrador.proyecto_integrador.model.Administrador;
import com.integrador.proyecto_integrador.model.Cliente;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class administradorController {
    @RequestMapping("/")
    private String inicio (Model model, HttpSession session)
    {
        String mensajeBienvenida;
        Object usuario = session.getAttribute("usuario");

        if (usuario instanceof Administrador) {
            Administrador administrador = (Administrador) usuario;
            mensajeBienvenida = "Hola " + administrador.getNombre_a() + "!";
        } else if (usuario instanceof Cliente) {
            Cliente cliente = (Cliente) usuario;
            mensajeBienvenida = "Hola " + cliente.getNombre() + "!";
        } else if (usuario != null) {
            // Si 'usuario' es un String o cualquier otro tipo
            mensajeBienvenida = "Hola " + usuario.toString() + "!";
        } else {
            // Caso donde el usuario no está logueado
            mensajeBienvenida = "Iniciar Sesion";
        }

        model.addAttribute("mensaje_ini", mensajeBienvenida);
        return "administrador";
    }
}
