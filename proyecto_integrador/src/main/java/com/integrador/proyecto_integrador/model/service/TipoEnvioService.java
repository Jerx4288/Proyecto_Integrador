package com.integrador.proyecto_integrador.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.ITipoEnvioDAO;
import com.integrador.proyecto_integrador.model.TipoEnvio;

@Service
public class TipoEnvioService implements ITipoEnvioService {
    
    @Autowired
    ITipoEnvioDAO tipoEnvioDAO;

    @Override
    public TipoEnvio buscarTipoEnvioPorNombre(String nombre) {
        // Devuelve el objeto TipoEnvio basado en el nombre
        return tipoEnvioDAO.findByTipo_e(nombre);  
    }
}
