package com.integrador.proyecto_integrador.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.proyecto_integrador.model.Administrador;
import com.integrador.proyecto_integrador.model.Boleta;
import com.integrador.proyecto_integrador.model.Citas;
import com.integrador.proyecto_integrador.model.ICitasDAO;
import com.integrador.proyecto_integrador.model.TortaClasica;
import com.integrador.proyecto_integrador.model.TortaEspecial;
import com.integrador.proyecto_integrador.model.Vela;
import com.integrador.proyecto_integrador.model.service.IAdministadorService;
import com.integrador.proyecto_integrador.model.service.IBoletaService;
import com.integrador.proyecto_integrador.model.service.ICitaService;
import com.integrador.proyecto_integrador.model.service.ITortaClaService;
import com.integrador.proyecto_integrador.model.service.ITortaEspecialService;
import com.integrador.proyecto_integrador.model.service.IVelaService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class administradorController {

    @Autowired
    private ITortaClaService tortaClaService;

    @Autowired
    private ITortaEspecialService tortaEspecialService;

    @Autowired
    private IVelaService VelaService;

    @Autowired
    private IAdministadorService administadorService;

    @Autowired
    private IBoletaService boletaService;

    @Autowired
    private ICitaService citaService;

    @RequestMapping("/")
    private String inicio (Model model, HttpSession session)
    {
        String dni;
        Object usuario = session.getAttribute("usuario");
        Administrador administrador = (Administrador) usuario;
        dni = administrador.getId_admin();
        System.out.println(dni);

        Administrador administrador2 = administadorService.buscarAdministrador(dni);
        System.out.println(administrador2.getPassword_a());
        model.addAttribute("administrador", administrador2);
        System.out.println(administrador2);

        return "administrador";
    }
    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    private String guardar(Administrador administrador, RedirectAttributes redirectAttributes)
    {
        String rpta = administadorService.guardarAdmin(administrador);
        redirectAttributes.addFlashAttribute("mensaje",rpta);
        return "redirect:/admin/";
    }
    
    @RequestMapping("/añadir")
    private String añadir(Model model, HttpSession session)
    {
        Administrador administrador = new Administrador();
        model.addAttribute("administrador", administrador);
        return "adminAñadir";
    }

    @RequestMapping("/tortas")
    private String tortas(Model model) throws JsonProcessingException
    {
        List<TortaClasica> listaTortasClasicas = tortaClaService.cargarTodasTortas();
        

        Map<String, Long> stockPorTortaClasica = listaTortasClasicas.stream()
        .collect(Collectors.groupingBy(
            TortaClasica::getNombre_tc, // Agrupar por nombre de la torta
            Collectors.summingLong(TortaClasica::getStock_tc) // Sumar stock
        ));


        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("labelsTortaClasica", mapper.writeValueAsString(new ArrayList<>(stockPorTortaClasica.keySet())));
        model.addAttribute("valuesTortaClasica", mapper.writeValueAsString(new ArrayList<>(stockPorTortaClasica.values())));
       
        model.addAttribute("listaTortac", tortaClaService.cargarTodasTortas());
        
        return "adminTortas";
    }

    @RequestMapping("/tortase")
    private String tortase (Model model) throws JsonProcessingException
    {
        List<TortaEspecial> listaTortasEspeciales = tortaEspecialService.cargarTodastort();

        Map<String, Long> stockPorTortaEspecial = listaTortasEspeciales.stream()
        .collect(Collectors.groupingBy(
            TortaEspecial::getNombre_te, // Agrupar por nombre de la torta
            Collectors.summingLong(TortaEspecial::getStock_te) // Sumar stock
        ));
        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("labelsTortaEspecial", mapper.writeValueAsString(new ArrayList<>(stockPorTortaEspecial.keySet())));
        model.addAttribute("valuesTortaEspecial", mapper.writeValueAsString(new ArrayList<>(stockPorTortaEspecial.values())));
  
        model.addAttribute("listaTortae", tortaEspecialService.cargarTodastort());

        return "adminTortae";
    }

    @RequestMapping("/velas")
    private String velas (Model model) throws JsonProcessingException
    {
        List<Vela> listaVelas = VelaService.cargaTodasVelas();  
        
        Map<String, Long> stockPorVelas = listaVelas.stream()
        .collect(Collectors.groupingBy(
            Vela::getModelo_vela, 
            Collectors.summingLong(Vela::getStock_vela)
        ));
        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("labelsVelas", mapper.writeValueAsString(new ArrayList<>(stockPorVelas.keySet())));
        model.addAttribute("valuesVelas", mapper.writeValueAsString(new ArrayList<>(stockPorVelas.values())));
        model.addAttribute("listaVelas", VelaService.cargaTodasVelas());
        return "adminVelas";
    }

    @RequestMapping("/editarTortaCla/{id}")
    public String editarStock(@PathVariable("id") String id, @RequestParam("stock") Integer stock, Model model) {
        TortaClasica tortaClasica = tortaClaService.buscarTortaClasica(id);
        if (tortaClasica != null) {
            tortaClasica.setStock_tc(stock); 
            tortaClaService.guardarTortaClasica(tortaClasica);
        }
        model.addAttribute("listaTortac", tortaClaService.cargarTodasTortas());
        return "redirect:/admin/tortas";
    }

    @RequestMapping("/editarTortaEs/{id}")
    public String editarStockTorta(@PathVariable("id") String id, @RequestParam("stock") Integer stock, Model model) {
        TortaEspecial tortaEspecial = tortaEspecialService.buscarTortaEspecial(id);
        if (tortaEspecial != null) {
            tortaEspecial.setStock_te(stock); 
            tortaEspecialService.guardarTortaEspecial(tortaEspecial); 
        }

        model.addAttribute("listaTortae", tortaClaService.cargarTodasTortas());
        return "redirect:/admin/tortase"; 
    }

    @RequestMapping("/editarVelas/{id}")
    public String editarStockVela(@PathVariable("id") String id, @RequestParam("stock") Integer stock, Model model) {
        Vela vela = VelaService.buscarVela(id);

        if (vela != null) {
            vela.setStock_vela(stock); 
            VelaService.guardarVela(vela);
        }
        model.addAttribute("listaVelas", VelaService.cargaTodasVelas());
        return "redirect:/admin/velas"; 
    }

    @RequestMapping("/boleta")
    private String boleta(@RequestParam(value = "mes", required = false) Integer mes,
                          @RequestParam(value = "anio", required = false) Integer anio,
                          @RequestParam(value = "dni_cliente", required = false) String dniCliente,
                          Model model) {
    
        List<String> listaDniClientes = boletaService.obtenerDnisClientes();
        List<Boleta> listaBoletas = new ArrayList<>();
        System.out.println("Mes: " + mes);
        System.out.println("Año: " + anio);
        System.out.println("DNI Cliente: '" + dniCliente + "'");
        try {
            if (mes != null && anio != null && (dniCliente == null || dniCliente.isEmpty())) {
                listaBoletas = boletaService.obtenerBoletasPorMesAnio(mes, anio);
                System.out.println("Entro 3");
            } else if (anio != null && dniCliente != null && mes == null) {
                listaBoletas = boletaService.obtenerBoletasPorAnioYCliente(anio, dniCliente);
                System.out.println("Entro 1");
            } else if (mes != null && anio != null && dniCliente != null) {
                listaBoletas = boletaService.obtenerBoletasPorMesAnioYCliente(mes, anio, dniCliente);
                System.out.println("Entro 2");
            } else if (dniCliente != null && mes == null && anio == null    ) {
                listaBoletas = boletaService.obtenerBoletasPorCliente(dniCliente);
                System.out.println("Entro 4");
            } else {
                listaBoletas = boletaService.obtenerTodasBoletas();
            }
            if (listaBoletas.isEmpty()) {
                model.addAttribute("mensaje", "No se encontraron boletas con los filtros aplicados.");
            }

            // Generar datos para el gráfico
            Map<String, Long> dataGrafico = listaBoletas.stream()
            .collect(Collectors.groupingBy(boleta -> boleta.getCliente().getDni(), Collectors.counting()));

            // Serializar los datos a JSON para enviarlos a la vista
            ObjectMapper mapper = new ObjectMapper();
            model.addAttribute("labelsJson", mapper.writeValueAsString(new ArrayList<>(dataGrafico.keySet())));
            model.addAttribute("valuesJson", mapper.writeValueAsString(new ArrayList<>(dataGrafico.values())));

        } catch (Exception e) {
            model.addAttribute("error", "Hubo un problema al obtener las boletas: " + e.getMessage());
            e.printStackTrace(); 
        }

        model.addAttribute("listaBoletas", listaBoletas);
        model.addAttribute("totalBoletas", listaBoletas.size());
        model.addAttribute("listaDniClientes", listaDniClientes);
        model.addAttribute("años", IntStream.range(2020, 2025).boxed().collect(Collectors.toList()));
        return "adminBoleta";
    }
    
    @RequestMapping("/citass")
    private String citas(@RequestParam(value = "mes", required = false) Integer mes,
    @RequestParam(value = "anio", required = false) Integer anio,
    @RequestParam(value = "dni_cliente", required = false) String dniCliente,
    Model model)
    {
        System.out.println("Mes: " + mes);
        System.out.println("Año: " + anio);
        System.out.println("DNI Cliente: '" + dniCliente + "'");
        List<String> listaDniClientes = citaService.obtenerDnisClientes();
        List<Citas> listaCitas = new ArrayList<>();
        try {
            if (anio != null && mes == null && (dniCliente == null || dniCliente.isEmpty())) {
                listaCitas = citaService.obtenerCitasPorAnio(anio);
                System.out.println("ENTRO SOLO POR AÑO " + listaCitas);
            } else if (mes != null && anio != null) {
                if (dniCliente == null || dniCliente.isEmpty()) {
                    listaCitas = citaService.obtenerCitasPorMesAnio(mes, anio);
                    System.out.println("ENTRO " + listaCitas);
                } else {
                    listaCitas = citaService.obtenerCitasPorMesAnioYCliente(mes, anio, dniCliente);
                    System.out.println("ENTRO 2 " + listaCitas);
                }
            } else if (anio != null && dniCliente != null && mes == null) {
                listaCitas = citaService.obtenerCitasPorAnioYCliente(anio, dniCliente);
            } else if (dniCliente != null && mes == null && anio == null) {
                listaCitas = citaService.obtenerCitasPorCliente(dniCliente);
            } else {
                listaCitas = citaService.obtenerTodasCitas();
            }
            
            if (listaCitas.isEmpty()) {
                model.addAttribute("mensaje", "No se encontraron boletas con los filtros aplicados.");
            }
            System.out.println(listaCitas);
            // Generar datos para el gráfico
            Map<String, Map<Integer, Long>> dataGrafico = listaCitas.stream()
            .collect(Collectors.groupingBy(cita -> cita.getCliente().getDni(),
                Collectors.groupingBy(cita -> cita.getFecha_c().getMonthValue(), Collectors.counting())));

            List<String> labels = new ArrayList<>();
            List<Long> values = new ArrayList<>();

            for (Map.Entry<String, Map<Integer, Long>> entry : dataGrafico.entrySet()) {
                String clienteDni = entry.getKey();
                Map<Integer, Long> mesesCitas = entry.getValue();
                for (Map.Entry<Integer, Long> mesEntry : mesesCitas.entrySet()) {
                    labels.add(clienteDni + " - Mes: " + mesEntry.getKey());
                    values.add(mesEntry.getValue());
                }
            }
    
            ObjectMapper mapper = new ObjectMapper();
            model.addAttribute("labelsJson", mapper.writeValueAsString(labels));
            model.addAttribute("valuesJson", mapper.writeValueAsString(values));

        } catch (Exception e) {
            model.addAttribute("error", "Hubo un problema al obtener las boletas: " + e.getMessage());
            e.printStackTrace(); 
        }
        model.addAttribute("listaCitas", listaCitas);
        model.addAttribute("totalCitas", listaCitas.size());
        model.addAttribute("listaDniClientes", listaDniClientes);
        model.addAttribute("años", IntStream.range(2020, 2025).boxed().collect(Collectors.toList()));

        return "adminCitas";
    }
    @RequestMapping("/uploads/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            // Ruta a la carpeta de imágenes
            Path path = Paths.get("C:/Users/mbjhe/OneDrive/Desktop/UTP/PROYECTO_FINAL_INTEGRADOR/proyecto_integrador/uploads/" + filename);
            Resource resource = new FileSystemResource(path);
            
            
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) 
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/eliminarCita/{id}", method = RequestMethod.POST)
    private String eliminarCita(@PathVariable(value = "id") Integer id) {
        String rpta = citaService.eliminarCita(id);
        System.out.println(rpta);
        return "redirect:/admin/citass"; 
    }
}
