package com.integrador.proyecto_integrador.model.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.ITortaClasicaDAO;
import com.integrador.proyecto_integrador.model.TortaClasica;

@Service
public class TortaClaService implements ITortaClaService {


    @Autowired
    private ITortaClasicaDAO tortaClasicaDAO;

    @Override
    public List<TortaClasica> cargarCategoriasFiltradas(String nombre, String tamano) {
        return tortaClasicaDAO.buscarPorNombreYTamano(nombre, tamano);
    }

    @Override
    public List<TortaClasica> cargarTortaClasicas(List<String> ids) {
        System.out.println("IDs recibidos: " + ids);  // Agregar esta línea para ver los IDs
        return tortaClasicaDAO.findByIdsIn(ids);
    }

    @Override
    public void guardarTortaClasica(TortaClasica tortaClasica) {
        tortaClasicaDAO.save(tortaClasica);
    }

    @Override
    public List<TortaClasica> cargarTodasTortas() {
        return (List<TortaClasica>) tortaClasicaDAO.findAll();
    }

    @Override
    public TortaClasica buscarTortaClasica(String id) {
        return tortaClasicaDAO.findById(id).get();
    }

    



    
    
}
