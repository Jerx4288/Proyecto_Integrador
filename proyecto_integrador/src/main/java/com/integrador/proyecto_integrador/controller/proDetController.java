package com.integrador.proyecto_integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.integrador.proyecto_integrador.model.Administrador;
import com.integrador.proyecto_integrador.model.Cliente;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/productodetalle")
public class proDetController {

    @RequestMapping("/")
    public String inicio(@RequestParam(value = "id", required = false) String productoId, Model model, HttpSession session) {
        if (session.getAttribute("usuario") != null) {
        Object usuario = session.getAttribute("usuario");
        String mensajeBienvenida;

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

        if (productoId == null) {
            return "tienda"; // Vista de tienda
        } else {
            System.out.println("Jalo bien id " + productoId);
            return "producto_detalle"; // Vista del detalle del producto
        }

    } else {
        model.addAttribute("mensaje_ini", "Iniciar Sesion");
    }

        System.out.println("Entro al controlador");
        return "producto_detalle"; // nombre de la vista Thymeleaf
    }
}
