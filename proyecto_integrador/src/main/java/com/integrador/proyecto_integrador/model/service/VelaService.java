package com.integrador.proyecto_integrador.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.IVelaDAO;
import com.integrador.proyecto_integrador.model.Vela;

@Service
public class VelaService implements IVelaService {

    @Autowired
    private IVelaDAO velaDAO;
    @Override
    public List<Vela> cargarVelas(List<String> ids) {
       System.out.println("ID VELAS RECIBIDO: " + ids);
       return velaDAO.findByIdsIn(ids);
    }
    @Override
    public List<Vela> cargarCategoriasFiltradas(String nombre) {
        return velaDAO.buscarPorNombre(nombre);
    }
    @Override
    public void guardarVela(Vela vela) {
        velaDAO.save(vela);
    }
    
}
