package com.integrador.proyecto_integrador.model.service;

import java.util.List;

import com.integrador.proyecto_integrador.model.Vela;

public interface IVelaService {
     public List<Vela> cargarCategoriasFiltradas(String nombre);
    public List<Vela> cargarVelas(List<String> ids);
    public void guardarVela(Vela vela);
}
