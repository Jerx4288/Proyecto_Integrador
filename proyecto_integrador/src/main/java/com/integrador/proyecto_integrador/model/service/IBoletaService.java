package com.integrador.proyecto_integrador.model.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.integrador.proyecto_integrador.model.Boleta;

import net.sf.jasperreports.engine.JRException;

public interface IBoletaService {
    public String guardarBoleta(Boleta boleta);
    public List <Boleta> cargarCategoriasFiltradas(String param);
    public List<Boleta> filtrarPorMesYAnio(String dni, Integer mes, Integer anio);
    public List<Boleta> obtenerBoletasPorMesAnioYCliente(Integer mes, Integer anio, String dni);
    public List<Boleta>  obtenerBoletasPorMesAnio(Integer mes, Integer anio);
    public List<Boleta> obtenerBoletasPorCliente(String dniCliente);
    public List<Boleta> obtenerTodasBoletas();
    public List<String> obtenerDnisClientes(); 
    public List<Boleta> obtenerBoletasPorAnioYCliente(Integer anio, String dniCliente);
    public byte[] exportPDF(Integer mes, Integer anio) throws JRException, FileNotFoundException;
    public byte[] exportXls() throws JRException, FileNotFoundException;
    public byte[] exportPDF() throws JRException, FileNotFoundException;
}