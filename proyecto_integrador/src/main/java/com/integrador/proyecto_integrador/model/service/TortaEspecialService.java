package com.integrador.proyecto_integrador.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.ITortaEspecialDAO;

import com.integrador.proyecto_integrador.model.TortaEspecial;

@Service
public class TortaEspecialService implements ITortaEspecialService 
{

    @Autowired
    private ITortaEspecialDAO tortaEspecialDAO;

    @Override
    public List<TortaEspecial> cargarCategoriasFiltradas(String nombre, String tamano, String relleno,String tipo_keke) 
    {
        
        return tortaEspecialDAO.buscarPorNombreYTamano(nombre, tamano, relleno,tipo_keke);
    }

    @Override
    public List<TortaEspecial> cargarTortaEspecial(List<String> ids) {
        System.out.println("IDs recibidos: " + ids);  // Agregar esta línea para ver los IDs
        return tortaEspecialDAO.findByIdsIn(ids);
    }
    
}
