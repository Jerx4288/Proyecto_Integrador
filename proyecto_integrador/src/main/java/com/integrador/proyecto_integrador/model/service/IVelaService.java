package com.integrador.proyecto_integrador.model.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.integrador.proyecto_integrador.model.Vela;

import net.sf.jasperreports.engine.JRException;

public interface IVelaService {
     public List<Vela> cargarCategoriasFiltradas(String nombre);
    public List<Vela> cargarVelas(List<String> ids);
    public void guardarVela(Vela vela);
    public List<Vela> cargaTodasVelas();
    public Vela buscarVela(String id);
    public byte[] exportPDF() throws JRException, FileNotFoundException;
    public byte[] exportXls() throws JRException, FileNotFoundException;
}
