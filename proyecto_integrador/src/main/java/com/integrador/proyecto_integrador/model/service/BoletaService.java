package com.integrador.proyecto_integrador.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.Boleta;
import com.integrador.proyecto_integrador.model.IBoletaDAO;

@Service
public class BoletaService implements IBoletaService{
    
    @Autowired
    IBoletaDAO boletaDAO;

    @Override
    public String guardarBoleta(Boleta boleta) {
        boletaDAO.save(boleta);  // Guarda la boleta en la base de datos
        return "redirect:/boleta";  // Redirige a la página de boletas (o la página que necesites)
    }

    @Override
    public List<Boleta> cargarCategoriasFiltradas(String param) {
        return boletaDAO.cualquierNombre(param);
    }

    @Override
    public List<Boleta> filtrarPorMesYAnio(String dni, Integer mes, Integer anio) 
    {
        return boletaDAO.findByDniAndMesYAnio(dni, mes, anio);
    }
    
}
