package com.integrador.proyecto_integrador.model.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.Citas;
import com.integrador.proyecto_integrador.model.ICitasDAO;

@Service
public class CitaService implements ICitaService
{

    @Autowired
    private ICitasDAO citasDAO;

    @Override
    
    public String guardarCita(Citas citas) {
        try {
            String rpta;
            if (citas.getId() != null && citasDAO.existsById(citas.getId())) {
                // La cita ya existe, se está actualizando
                rpta = "Cita de " + citas.getNombre_c() + " modificada correctamente.";
            } else {
                // Es una nueva cita
                rpta = "Cita de " + citas.getNombre_c() + " insertada correctamente.";
            }

            // Guarda la cita en la base de datos
            citasDAO.save(citas);  // Este método debería persistir la cita en la BD
            return rpta;

        } catch (Exception e) {
            // Manejo de errores con un mensaje más descriptivo
            return "Error al guardar la cita: " + e.getMessage();
        }
    }

    @Override
    public List<Citas> cargarCitasFiltradas(String param) {
        return citasDAO.cualquierNombre(param);
    }

    @Override
    public List<Citas> filtrarPorMesYAnio(String dni, Integer mes, Integer anio) {
        return citasDAO.findByDniAndMesYAnio(dni, mes, anio);
    }

    @Override
    public List<Citas> obtenerCitasPorMesAnio(Integer mes, Integer anio) {
        System.out.println("Mes: " + mes + ", Año: " + anio);
        List<Citas> citas = citasDAO.findByMesAndAnio(mes, anio);
        System.out.println("Citas encontradas: " + citas.size());
        return citas;
    }

    @Override
    public List<Citas> obtenerCitasPorCliente(String dniCliente) {
        return citasDAO.findByClienteDni(dniCliente);
    }

    @Override
    public List<Citas> obtenerTodasCitas() {
        return (List<Citas>) citasDAO.findAll();
    }

    @Override
    public List<String> obtenerDnisClientes() {
        return citasDAO.findDistinctDniClientes();
    }

    @Override
    public List<Citas> obtenerCitasPorAnioYCliente(Integer anio, String dniCliente) {
        return citasDAO.findByAnioAndClienteDni(anio, dniCliente);
    }

    @Override
    public List<Citas> obtenerCitasPorMesAnioYCliente(Integer mes, Integer anio, String dni) {
        return citasDAO.findByMesAndAnioAndClienteDni(mes, anio, dni);
    }

    @Override
    public List<Citas> obtenerCitasPorAnio(Integer anio) {
        return citasDAO.findByAnio(anio);
    }

    @Override
    public String eliminarCita(Integer id) {
        try {
            Citas citas = buscarCita(id);
            citasDAO.deleteById(id);
            return "Se elimino " + citas.getId();
        } catch (Exception e) {
            return "Error al eliminar categoria " + e.getMessage();
        }
    }

    @Override
    public Citas buscarCita(Integer id) {
        return citasDAO.findById(id).get();
    }

    @Override
    public Boolean existeCita(LocalDate fecha, LocalTime hora) {
        return citasDAO.existsByFechaAndHora(fecha, hora);
    }

    
}
