package com.integrador.proyecto_integrador.model.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.integrador.proyecto_integrador.model.TortaEspecial;

import net.sf.jasperreports.engine.JRException;

public interface ITortaEspecialService {
    public List<TortaEspecial> cargarCategoriasFiltradas(String nombre, String tamano, String relleno, String tipo_keke);
    public List<TortaEspecial> cargarTortaEspecial(List<String> ids);
    public void guardarTortaEspecial(TortaEspecial tortaEspecial);
    public List<TortaEspecial> cargarTodastort();
    public TortaEspecial buscarTortaEspecial(String id);
     public byte[] exportPDF() throws JRException, FileNotFoundException;
    public byte[] exportXls() throws JRException, FileNotFoundException;
}
