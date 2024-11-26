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
import com.integrador.proyecto_integrador.model.Vela;
import com.integrador.proyecto_integrador.model.service.IBoletaService;
import com.integrador.proyecto_integrador.model.service.ITipoEnvioService;
import com.integrador.proyecto_integrador.model.service.ITortaClaService;
import com.integrador.proyecto_integrador.model.service.ITortaEspecialService;
import com.integrador.proyecto_integrador.model.service.IVelaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class boletaController 
{
    @Autowired
    private ITortaClaService tortaClasicaService;

    @Autowired
    private IBoletaService boletaService;

    @Autowired
    private IVelaService velaService;
    @Autowired
    private ITipoEnvioService tipoEnvioService;

    @Autowired
    private ITortaEspecialService tortaEspecialService;

    @PostMapping("/boleta")
    public String recibirProductosCarrito(@RequestBody Map<String, List<Map<String, Object>>> payload, 
                                        HttpSession session, Model model) {
        // Obtener los productos desde el payload
        List<Map<String, Object>> productos = payload.get("productos");
        System.out.println(productos);
        List<TortaClasica> tortasClasicas = new ArrayList<>();
        List<TortaEspecial> tortasEspeciales = new ArrayList<>();
        List<Vela> velas = new ArrayList<>();
        int cantidadTotal = 0;
        int cantidadTotalVelas = 0; // Variable para contar todas las velas
        int cantidadVela = 0;
        
        for (Map<String, Object> producto : productos) {
            // Obtener los atributos comunes
            String nombre = producto.getOrDefault("nombre", "").toString();
            String tamanio = producto.getOrDefault("tamanio", "").toString();
            String relleno = producto.getOrDefault("relleno", "").toString();
            String tipo = producto.getOrDefault("tipo", "").toString();

            // Clasificación del producto según sus atributos
            if (nombre.toLowerCase().contains("vela") && producto.get("tamanio").toString().isEmpty() && producto.get("relleno").toString().isEmpty() && producto.get("tipo").toString().isEmpty()) 
            {
                List<Vela> velasFiltradas = velaService.cargarCategoriasFiltradas(nombre);
                velas.addAll(velasFiltradas);
                cantidadVela = Integer.parseInt(producto.getOrDefault("cantidad", "1").toString());
                cantidadTotalVelas += cantidadVela; // Sumar directamente las cantidades de velas


                // Imprimir detalles para depuración
                for (Vela vela : velasFiltradas) {
                    System.out.println("Vela - ID: " + vela.getId_vela());
                    cantidadTotal += cantidadVela;
                    
                }
            } else if (!relleno.isEmpty() && !tipo.isEmpty()) {
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
        System.out.println("-----------------------");
        System.out.println(velas);
        System.out.println("Cantidades vela: " + cantidadTotalVelas);
        // Agregar las listas de productos al modelo y sesión
        session.setAttribute("tortasClasicas", tortasClasicas);
        session.setAttribute("tortasEspeciales", tortasEspeciales);
        session.setAttribute("velas", velas);
        session.setAttribute("cantidadVela", cantidadTotalVelas);
        model.addAttribute("tortasClasicas", tortasClasicas);
        model.addAttribute("tortasEspeciales", tortasEspeciales);
        model.addAttribute("velas", velas);
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

        @SuppressWarnings("unchecked")
        List<Vela> velas = (List<Vela>) session.getAttribute("velas");

        Object cantidadVelaObj = session.getAttribute("cantidadVela");
        Integer cantidadVela = 0;  // Valor predeterminado
        if (cantidadVelaObj instanceof Integer) {
            cantidadVela = (Integer) cantidadVelaObj;
        }
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

        if (velas != null && cantidadVela != null) {
            cantidadTotal += cantidadVela;  // Sumar la cantidad de velas
            for (Vela vela : velas) {
                precioTotal += vela.getPrecio_vela() * cantidadVela;  // Sumar el precio total de las velas
            }
        }

        // Agregar las listas al modelo
        System.out.println("PARTE - GET");
        System.out.println(tortasClasicas);
        System.out.println(tortasEspeciales);
        System.out.println(velas);
        System.out.println(cantidadTotal);
        System.out.println(precioTotal);
        model.addAttribute("tortasClasicas", tortasClasicas);
        model.addAttribute("tortasEspeciales", tortasEspeciales);
        model.addAttribute("velas", velas);
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
        System.out.println("tipo de envio = " + entrega  );
        // Dedicatoria
        // Imprimir los datos recibidos para verificar
        @SuppressWarnings("unchecked")
        List<TortaClasica> tortasClasicas = (List<TortaClasica>) session.getAttribute("tortasClasicas");
        @SuppressWarnings("unchecked")
        List<TortaEspecial> tortasEspeciales = (List<TortaEspecial>) session.getAttribute("tortasEspeciales");
        @SuppressWarnings("unchecked")
        List<Vela> velas = (List<Vela>) session.getAttribute("velas");

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

        if (velas != null)
        {
            System.out.println("Velas:");
            for (Vela vela : velas)
            {
                System.out.println("ID de vela: " + vela.getId_vela());
            }
        }

        List<String> tortasClasicasIds = new ArrayList<>();
        List<String> tortasEspecialesIds = new ArrayList<>();
        List<String> velaIds = new ArrayList<>();

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

        if (velas != null)
        {
            for (Vela vela : velas)
            {
                velaIds.add(vela.getId_vela());
            }
        }
        

        // Cargar las tortas clásicas y especiales según los IDs recibidos
        List<TortaClasica> productosClasicasList = new ArrayList<>();
        List<TortaEspecial> productosEspecialesList = new ArrayList<>();
        List<Vela> productosVelasList = new ArrayList<>();

        if (!tortasClasicasIds.isEmpty()) {
            productosClasicasList = tortaClasicaService.cargarTortaClasicas(tortasClasicasIds);
        }

        if (!tortasEspecialesIds.isEmpty()) {
            productosEspecialesList = tortaEspecialService.cargarTortaEspecial(tortasEspecialesIds);
        }

        if (!velaIds.isEmpty())
        {
            productosVelasList = velaService.cargarVelas(velaIds);
        }
        // Combinamos ambas listas de productos
        List<Object> productosList = new ArrayList<>();
        productosList.addAll(productosClasicasList);
        productosList.addAll(productosEspecialesList);
        productosList.addAll(productosVelasList);

        System.out.println("Productos cargados: " + productosList);
        
        // Obtener el usuario desde la sesión
        Object usuario = session.getAttribute("usuario");
        System.out.println("Tipo de usuario en sesión: " + usuario);

        if (usuario instanceof Cliente) {
            Cliente cliente = (Cliente) usuario;
            // Buscar el tipo de envío por su nombre
            TipoEnvio tipoEnvio = tipoEnvioService.buscarTipoEnvioPorNombre(entrega);
            System.out.println(tipoEnvio);
            if (tipoEnvio == null) {
                redirectAttributes.addFlashAttribute("error", "Tipo de envío no válido.");
                System.out.println("Error: Tipo de envío no válido.");
                return "redirect:/boleta";
            }
            // Validar si hay suficiente stock
            for (Object producto : productosList) {
                if (producto instanceof Vela) {
                    Vela vela = (Vela) producto;
                    if (vela.getStock_vela() < cantidad) {
                        redirectAttributes.addFlashAttribute("error", "No hay suficiente stock de la vela: " + vela.getModelo_vela() + ".");
                        return "redirect:/boleta"; // Detener la compra si no hay stock suficiente
                    }
                } else if (producto instanceof TortaClasica) {
                    TortaClasica tortaClasica = (TortaClasica) producto;
                    if (tortaClasica.getStock_tc() < cantidad) {
                        redirectAttributes.addFlashAttribute("error", "No hay suficiente stock de la torta clásica: " + tortaClasica.getNombre_tc() + ".");
                        return "redirect:/boleta"; // Detener la compra si no hay stock suficiente
                    }
                } else if (producto instanceof TortaEspecial) {
                    TortaEspecial tortaEspecial = (TortaEspecial) producto;
                    if (tortaEspecial.getStock_te() < cantidad) {
                        redirectAttributes.addFlashAttribute("error", "No hay suficiente stock de la torta especial: " + tortaEspecial.getNombre_te() + ".");
                        return "redirect:/boleta"; // Detener la compra si no hay stock suficiente
                    }
                }
            }
            // Crear la boleta
            Boleta boleta = new Boleta(null, fechaCompra, totalCompra, metodoPago, dedicatoria, productosList, cantidad, cliente, tipoEnvio);

            // Asociar la boleta con las tortas
            for (Object producto : productosList) {
                if (producto instanceof Vela) {
                    Vela vela = (Vela) producto;
                    vela.getBoletas().add(boleta); // Asegúrate de que la relación con boletas esté bien configurada en la entidad Vela
                    vela.setStock_vela(vela.getStock_vela() - cantidad); // Ajuste del stock según la cantidad
                    velaService.guardarVela(vela);
                } else if (producto instanceof TortaClasica) {
                    TortaClasica tortaClasica = (TortaClasica) producto;
                    tortaClasica.getBoletas().add(boleta);
                    tortaClasica.setStock_tc(tortaClasica.getStock_tc() - cantidad); // Ajuste del stock según la cantidad
                    tortaClasicaService.guardarTortaClasica(tortaClasica); // Guardar los cambios en la base de datos
                } else if (producto instanceof TortaEspecial) {
                    TortaEspecial tortaEspecial = (TortaEspecial) producto;
                    tortaEspecial.getBoletas().add(boleta);
                    tortaEspecial.setStock_te(tortaEspecial.getStock_te() - cantidad); // Ajuste del stock según la cantidad
                    tortaEspecialService.guardarTortaEspecial(tortaEspecial);
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




