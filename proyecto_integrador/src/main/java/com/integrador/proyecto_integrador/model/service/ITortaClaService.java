package com.integrador.proyecto_integrador.model.service;

import java.util.List;

import com.integrador.proyecto_integrador.model.TortaClasica;

public interface ITortaClaService {
    public List <TortaClasica> cargarCategoriasFiltradas(String nombre, String tamano);
}
