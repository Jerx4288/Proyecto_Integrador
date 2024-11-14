package com.integrador.proyecto_integrador.model.service;

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
    
}
