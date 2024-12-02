package com.integrador.proyecto_integrador.model.service;

import java.util.List;

import com.integrador.proyecto_integrador.model.TortaClasica;

public interface ITortaClaService {
    public List<TortaClasica> cargarCategoriasFiltradas(String nombre, String tamano);
    public List<TortaClasica> cargarTortaClasicas(List<String> ids);
    public void guardarTortaClasica(TortaClasica tortaClasica);
    public List<TortaClasica> cargarTodasTortas();
    public TortaClasica buscarTortaClasica(String id);
}
