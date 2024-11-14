package com.integrador.proyecto_integrador.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.integrador.proyecto_integrador.model.Administrador;
import com.integrador.proyecto_integrador.model.Boleta;
import com.integrador.proyecto_integrador.model.Cliente;
import com.integrador.proyecto_integrador.model.TipoEnvio;
import com.integrador.proyecto_integrador.model.TortaClasica;

import com.integrador.proyecto_integrador.model.service.IBoletaService;
import com.integrador.proyecto_integrador.model.service.ITipoEnvioService;
import com.integrador.proyecto_integrador.model.service.ITortaClaService;


import jakarta.servlet.http.HttpSession;

@Controller
public class boletaController 
{
    @Autowired
    private ITortaClaService tortaClasicaService;

    @Autowired
    private IBoletaService boletaService;


    @Autowired
    private ITipoEnvioService tipoEnvioService;

    @PostMapping("/boleta")
    public String recibirProductosCarrito(@RequestBody Map<String, List<Map<String, Object>>> payload, 
                                        HttpSession session, Model model) 
    {
        // Obtener los productos desde el payload
        List<Map<String, Object>> productos = payload.get("productos");
        List<TortaClasica> tortas = new ArrayList<>();  // Lista para acumular todas las tortas
        int cantidadTotal = 0;
        for (Map<String, Object> producto : productos) {
            // Imprimir los detalles del producto
            System.out.println("Nombre del producto: " + producto.get("nombre"));
            System.out.println("Tamaño: " + producto.get("tamanio"));
            System.out.println("Precio total: " + producto.get("precioTotal"));

            // Obtener nombre y tamaño del producto
            String nombre = producto.get("nombre") != null ? producto.get("nombre").toString() : "";
            String tamanio = producto.get("tamanio") != null ? producto.get("tamanio").toString() : "";
            
            // Cargar las tortas filtradas y agregarlas a la lista
            List<TortaClasica> tortasFiltradas = tortaClasicaService.cargarCategoriasFiltradas(nombre, tamanio);
            tortas.addAll(tortasFiltradas);
            
            for (TortaClasica torta : tortasFiltradas) {
                System.out.println("ID: " + torta.getId_tortac());
                cantidadTotal++;
            }
            System.out.println("-------------------------------------------------------");
        }
        System.out.println(cantidadTotal);
        // Agregar la lista completa de tortas al modelo
        session.setAttribute("tortas", tortas);
        model.addAttribute("tortas", tortas);
        model.addAttribute("cantidadTotal", cantidadTotal);
        // Retornar la vista boleta
        return "redirect:/boleta";
    }

    @GetMapping("/boleta")
    public String mostrarBoleta(Model model, HttpSession session) {
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
    
        @SuppressWarnings("unchecked")
        List<TortaClasica> tortas = (List<TortaClasica>) session.getAttribute("tortas");
    
        if (tortas != null) {
            model.addAttribute("tortas", tortas);
    
            // Calcular la cantidad total de productos
            int cantidadTotal = tortas.size();  // Aquí obtenemos el número total de productos
            model.addAttribute("cantidadTotal", cantidadTotal);
    
            // Calcular el precio total sumando los precios de todas las tortas
            double precioTotal = tortas.stream().mapToDouble(TortaClasica::getPrecio_tc).sum();
            model.addAttribute("precioTotal", precioTotal);
        }
    
        return "boleta";
    }


    @RequestMapping("boleta/guardar")
public String finalizar(@RequestParam("productos") String productos,
                        @RequestParam("cantidad") int cantidad,
                        @RequestParam("metodo_pago") String metodoPago,
                        @RequestParam("entrega") String entrega,
                        @RequestParam("direccion") String direccion,
                        @RequestParam("total") double total,
                        @RequestParam("fecha_compra") String fechaCompra,
                        @RequestParam("dedicatoria") String dedicatoria,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {

    System.out.println("Entró al último controlador");

    // Imprimir los valores para depuración
    System.out.println("Productos: " + productos);
    System.out.println("Método de Pago: " + metodoPago);
    System.out.println("Tipo de Entrega: " + entrega);
    System.out.println("Dirección: " + direccion);
    System.out.println("Total: " + total);
    System.out.println("Cantidad: " + cantidad);
    System.out.println("Fecha de compra: " + fechaCompra);
    System.out.println("Dedicatoria: " + dedicatoria);

    // Convertir el parámetro "productos" en una lista de IDs
    List<String> productosIds = Arrays.asList(productos.split(","));
    System.out.println("Lista de productos IDs: " + productosIds);

    // Cargar las tortas clásicas según los IDs recibidos
    List<TortaClasica> productosList = tortaClasicaService.cargarTortaClasicas(productosIds);
    System.out.println("Productos cargados: " + productosList);

    // Obtener el usuario desde la sesión
    Object usuario = session.getAttribute("usuario");

    if (usuario instanceof Cliente) {
        Cliente cliente = (Cliente) usuario;

        // Buscar el tipo de envío por su nombre
        TipoEnvio tipoEnvio = tipoEnvioService.buscarTipoEnvioPorNombre(entrega);
        if (tipoEnvio == null) {
            redirectAttributes.addFlashAttribute("error", "Tipo de envío no válido.");
            System.out.println("Error: Tipo de envío no válido.");
            return "redirect:/boleta";
        }

        // Crear la boleta
        Boleta boleta = new Boleta(null, fechaCompra, total, metodoPago, dedicatoria, productosList, cantidad, cliente, tipoEnvio);

        // Asociar la boleta con las tortas
        for (TortaClasica torta : productosList) {
            torta.getBoletas();  // Solo cargar la colección, no modificarla aún.
        }
        // Luego de terminar de iterar, modifica la colección fuera del ciclo.
        for (TortaClasica torta : productosList) {
            torta.getBoletas().add(boleta);
        }

        // Guardar la boleta en la base de datos
        boletaService.guardarBoleta(boleta);
        System.out.println("Boleta guardada exitosamente.");

        // Redirigir a la página de boleta
        return "redirect:/boleta";

    } else {
        // Si no hay un cliente en la sesión, redirigir al login
        redirectAttributes.addFlashAttribute("error", "No se encontró al cliente en la sesión.");
        System.out.println("Error: No se encontró al cliente en la sesión.");
        return "redirect:/login/";
    }
}

}
