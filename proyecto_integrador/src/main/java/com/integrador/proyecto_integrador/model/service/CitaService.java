package com.integrador.proyecto_integrador.model.service;

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

    
}
