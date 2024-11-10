package com.integrador.proyecto_integrador.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.ITortaClasicaDAO;
import com.integrador.proyecto_integrador.model.TortaClasica;

@Service
public class TortaClaService implements ITortaClaService {


    @Autowired
    private ITortaClasicaDAO ITortaClasicaDAO;

    @Override
    public List<TortaClasica> cargarCategoriasFiltradas(String nombre, String tamano) {
        return ITortaClasicaDAO.buscarPorNombreYTamano(nombre, tamano);
    }
    
}
