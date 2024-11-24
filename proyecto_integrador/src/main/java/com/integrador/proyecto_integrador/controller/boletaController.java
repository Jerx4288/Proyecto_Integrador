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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.integrador.proyecto_integrador.model.Administrador;
import com.integrador.proyecto_integrador.model.Boleta;
import com.integrador.proyecto_integrador.model.Cliente;
import com.integrador.proyecto_integrador.model.TipoEnvio;
import com.integrador.proyecto_integrador.model.TortaClasica;
import com.integrador.proyecto_integrador.model.TortaEspecial;
import com.integrador.proyecto_integrador.model.service.IBoletaService;
import com.integrador.proyecto_integrador.model.service.ITipoEnvioService;
import com.integrador.proyecto_integrador.model.service.ITortaClaService;
import com.integrador.proyecto_integrador.model.service.ITortaEspecialService;

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

    @Autowired
    private ITortaEspecialService tortaEspecialService;

    @PostMapping("/boleta")
public String recibirProductosCarrito(@RequestBody Map<String, List<Map<String, Object>>> payload, 
                                      HttpSession session, Model model) {
    // Obtener los productos desde el payload
    List<Map<String, Object>> productos = payload.get("productos");
    List<TortaClasica> tortasClasicas = new ArrayList<>();
    List<TortaEspecial> tortasEspeciales = new ArrayList<>();
    int cantidadTotal = 0;

    for (Map<String, Object> producto : productos) {
        // Obtener los atributos comunes
        String nombre = producto.getOrDefault("nombre", "").toString();
        String tamanio = producto.getOrDefault("tamanio", "").toString();
        String relleno = producto.getOrDefault("relleno", "").toString();
        String tipo = producto.getOrDefault("tipo", "").toString();

        // Clasificación del producto según sus atributos
        if (!relleno.isEmpty() && !tipo.isEmpty()) {
            // Si tiene relleno y tipo, lo consideramos como una torta especial
            List<TortaEspecial> especialesFiltradas = tortaEspecialService.cargarCategoriasFiltradas(nombre, tamanio, relleno, tipo);
            tortasEspeciales.addAll(especialesFiltradas);
            cantidadTotal += especialesFiltradas.size();
            
            // Imprimir detalles para depuración
            for (TortaEspecial torta : especialesFiltradas) {
                System.out.println("Torta Especial - ID: " + torta.getId_tortae());
            }
        } else if (producto.containsKey("nombre") && producto.containsKey("tamanio")) {
            // Si solo tiene nombre y tamaño, lo consideramos como una torta clásica
            List<TortaClasica> clasicasFiltradas = tortaClasicaService.cargarCategoriasFiltradas(nombre, tamanio);
            tortasClasicas.addAll(clasicasFiltradas);
            cantidadTotal += clasicasFiltradas.size();

            // Imprimir detalles para depuración
            for (TortaClasica torta : clasicasFiltradas) {
                System.out.println("Torta Clásica - ID: " + torta.getId_tortac());
            }
        }
    }

    System.out.println("Cantidad total de productos en el carrito: " + cantidadTotal);
    System.out.println(tortasEspeciales);
    System.out.println("-----------------------");
    System.out.println(tortasClasicas);
    // Agregar las listas de productos al modelo y sesión
    session.setAttribute("tortasClasicas", tortasClasicas);
    session.setAttribute("tortasEspeciales", tortasEspeciales);
    model.addAttribute("tortasClasicas", tortasClasicas);
    model.addAttribute("tortasEspeciales", tortasEspeciales);
    model.addAttribute("cantidadTotal", cantidadTotal);

    // Redirigir a la vista boleta
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

    // Inicializar variables para cantidad y precio total
    int cantidadTotal = 0;
    double precioTotal = 0.0;

    // Obtener las listas de tortas clásicas y especiales desde la sesión
    @SuppressWarnings("unchecked")
    List<TortaClasica> tortasClasicas = (List<TortaClasica>) session.getAttribute("tortasClasicas");
    @SuppressWarnings("unchecked")
    List<TortaEspecial> tortasEspeciales = (List<TortaEspecial>) session.getAttribute("tortasEspeciales");

    // Calcular totales
    if (tortasClasicas != null) {
        cantidadTotal += tortasClasicas.size();  // Aumentar por cantidad de tortas clásicas
        for (TortaClasica torta : tortasClasicas) {
            precioTotal += torta.getPrecio_tc();  // Sumar el precio de cada torta clásica
        }
    }

    if (tortasEspeciales != null) {
        cantidadTotal += tortasEspeciales.size();  // Aumentar por cantidad de tortas especiales
        for (TortaEspecial torta : tortasEspeciales) {
            precioTotal += torta.getPrecio_te();  // Sumar el precio de cada torta especial
        }
    }

    // Agregar las listas al modelo
    System.out.println("PARTE - GET");
    System.out.println(tortasClasicas);
    System.out.println(tortasEspeciales);
    model.addAttribute("tortasClasicas", tortasClasicas);
    model.addAttribute("tortasEspeciales", tortasEspeciales);

    // Agregar los totales al modelo
    model.addAttribute("cantidadTotal", cantidadTotal);
    model.addAttribute("precioTotal", precioTotal);

    // Retornar la vista boleta
    return "boleta";
}

@PostMapping("/guardar")
public String guardarCompra(@RequestParam("dedicatoria") String dedicatoria,
                            @RequestParam("fechaCompra") String fechaCompra,
                            @RequestParam("total") double totalCompra,
                            @RequestParam("metodoPago") String metodoPago,
                            @RequestParam("entrega") String entrega,
                            @RequestParam("cantidad") int cantidad,
                            HttpSession session, RedirectAttributes redirectAttributes)  {
    // Imprimir los datos recibidos del formulario
    System.out.println("ultimo controlador");
    // Dedicatoria
    // Imprimir los datos recibidos para verificar
    @SuppressWarnings("unchecked")
    List<TortaClasica> tortasClasicas = (List<TortaClasica>) session.getAttribute("tortasClasicas");
    @SuppressWarnings("unchecked")
    List<TortaEspecial> tortasEspeciales = (List<TortaEspecial>) session.getAttribute("tortasEspeciales");

    // Imprimir las IDs de las tortas clásicas
    if (tortasClasicas != null) {
        System.out.println("Tortas clásicas:");
        for (TortaClasica torta : tortasClasicas) {
            System.out.println("ID de torta clásica: " + torta.getId_tortac());
        }
    } else {
        System.out.println("No hay tortas clásicas en la sesión.");
    }

    // Imprimir las IDs de las tortas especiales
    if (tortasEspeciales != null) {
        System.out.println("Tortas especiales:");
        for (TortaEspecial torta : tortasEspeciales) {
            System.out.println("ID de torta especial: " + torta.getId_tortae());
        }
    } else {
        System.out.println("No hay tortas especiales en la sesión.");
    }

    List<String> tortasClasicasIds = new ArrayList<>();
    List<String> tortasEspecialesIds = new ArrayList<>();

    if (tortasClasicas != null) {
        for (TortaClasica torta : tortasClasicas) {
            tortasClasicasIds.add(torta.getId_tortac());
        }
    }

    if (tortasEspeciales != null) {
        for (TortaEspecial torta : tortasEspeciales) {
            tortasEspecialesIds.add(torta.getId_tortae());
        }
    }

    // Cargar las tortas clásicas y especiales según los IDs recibidos
    List<TortaClasica> productosClasicasList = new ArrayList<>();
    List<TortaEspecial> productosEspecialesList = new ArrayList<>();

    if (!tortasClasicasIds.isEmpty()) {
        productosClasicasList = tortaClasicaService.cargarTortaClasicas(tortasClasicasIds);
    }

    if (!tortasEspecialesIds.isEmpty()) {
        productosEspecialesList = tortaEspecialService.cargarTortaEspecial(tortasEspecialesIds);
    }

    // Combinamos ambas listas de productos
    List<Object> productosList = new ArrayList<>();
    productosList.addAll(productosClasicasList);
    productosList.addAll(productosEspecialesList);

    System.out.println("Productos cargados: " + productosList);
    
    // Obtener el usuario desde la sesión
    Object usuario = session.getAttribute("usuario");
    System.out.println("Tipo de usuario en sesión: " + usuario);

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
        Boleta boleta = new Boleta(null, fechaCompra, totalCompra, metodoPago, dedicatoria, productosList, cantidad, cliente, tipoEnvio);

        // Asociar la boleta con las tortas
        for (Object producto : productosList) {
            if (producto instanceof TortaClasica) {
                TortaClasica tortaClasica = (TortaClasica) producto;
                tortaClasica.getBoletas().add(boleta);
            } else if (producto instanceof TortaEspecial) {
                TortaEspecial tortaEspecial = (TortaEspecial) producto;
                tortaEspecial.getBoletas().add(boleta);
            }
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




