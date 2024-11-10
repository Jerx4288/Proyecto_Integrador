package com.integrador.proyecto_integrador.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.integrador.proyecto_integrador.model.TortaClasica;

import com.integrador.proyecto_integrador.model.service.TortaClaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class boletaController 
{
    @Autowired
    private TortaClaService tortaClasicaService;

    @PostMapping("/boleta")
    public String recibirProductosCarrito(@RequestBody Map<String, List<Map<String, Object>>> payload, 
                                        HttpSession session, Model model) 
    {
        // Verificar si el usuario está logueado y agregar mensaje de bienvenida
        if (session.getAttribute("usuario") != null) {
            String usuario = (String) session.getAttribute("usuario");
            model.addAttribute("mensaje_ini", "Hola " + usuario + "!");
        } else {
            model.addAttribute("mensaje_ini", "Iniciar Sesion");
        }

        // Obtener los productos desde el payload
        List<Map<String, Object>> productos = payload.get("productos");
        List<TortaClasica> tortas = new ArrayList<>();  // Lista para acumular todas las tortas

        for (Map<String, Object> producto : productos) {
            // Imprimir los detalles del producto
            System.out.println("Nombre del producto: " + producto.get("nombre"));
            System.out.println("Tamaño: " + producto.get("tamanio"));
            System.out.println("Precio total: " + producto.get("precioTotal"));
            System.out.println("Dedicatoria: " + producto.get("dedicatoria"));

            // Obtener nombre y tamaño del producto
            String nombre = producto.get("nombre") != null ? producto.get("nombre").toString() : "";
            String tamanio = producto.get("tamanio") != null ? producto.get("tamanio").toString() : "";

            // Cargar las tortas filtradas y agregarlas a la lista
            tortas.addAll(tortaClasicaService.cargarCategoriasFiltradas(nombre, tamanio));
        }

        // Agregar la lista completa de tortas al modelo
        model.addAttribute("tortas", tortas);

        // Retornar la vista boleta
        return "boleta";
    }

    @GetMapping("/boleta")
    public String mostrarBoleta(Model model, HttpSession session) {
        // Verificar si el usuario está logueado y agregar mensaje de bienvenida
        if (session.getAttribute("usuario") != null) {
            String usuario = (String) session.getAttribute("usuario");
            model.addAttribute("mensaje_ini", "Hola " + usuario + "!");
        } else {
            model.addAttribute("mensaje_ini", "Iniciar Sesion");
        }

        // Retornar la vista boleta con los datos en el modelo
        return "boleta"; 
    }

}
