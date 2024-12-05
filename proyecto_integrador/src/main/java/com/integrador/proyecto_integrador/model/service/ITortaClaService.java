package com.integrador.proyecto_integrador.model.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.integrador.proyecto_integrador.model.TortaClasica;

import net.sf.jasperreports.engine.JRException;

public interface ITortaClaService {
    public List<TortaClasica> cargarCategoriasFiltradas(String nombre, String tamano);
    public List<TortaClasica> cargarTortaClasicas(List<String> ids);
    public void guardarTortaClasica(TortaClasica tortaClasica);
    public List<TortaClasica> cargarTodasTortas();
    public TortaClasica buscarTortaClasica(String id);
    public byte[] exportPDF() throws JRException, FileNotFoundException;
    public byte[] exportXls() throws JRException, FileNotFoundException;
}
