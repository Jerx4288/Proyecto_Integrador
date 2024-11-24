package com.integrador.proyecto_integrador.model.service;

import java.util.List;

import com.integrador.proyecto_integrador.model.TortaEspecial;

public interface ITortaEspecialService {
    public List<TortaEspecial> cargarCategoriasFiltradas(String nombre, String tamano, String relleno, String tipo_keke);
    public List<TortaEspecial> cargarTortaEspecial(List<String> ids);
}
