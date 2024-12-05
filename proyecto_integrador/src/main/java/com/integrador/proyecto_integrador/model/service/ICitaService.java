package com.integrador.proyecto_integrador.model.service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import com.integrador.proyecto_integrador.model.Citas;

import net.sf.jasperreports.engine.JRException;

public interface ICitaService {
    public String guardarCita(Citas citas);
    public List <Citas> cargarCitasFiltradas(String param);
    public List<Citas> filtrarPorMesYAnio(String dni, Integer mes, Integer anio);
    public List<Citas> obtenerCitasPorMesAnioYCliente(Integer mes, Integer anio, String dni);
    public String eliminarCita(Integer id);
    public Citas buscarCita(Integer id);
    public List<Citas>  obtenerCitasPorMesAnio(Integer mes, Integer anio);
    public List<Citas> obtenerCitasPorAnio(Integer anio);
    public List<Citas> obtenerCitasPorCliente(String dniCliente);
    public List<Citas> obtenerTodasCitas();
    public List<String> obtenerDnisClientes(); 
    public List<Citas> obtenerCitasPorAnioYCliente(Integer anio, String dniCliente);
    public Boolean existeCita(LocalDate fecha, LocalTime hora);
    public byte[] exportPDF() throws JRException, FileNotFoundException;
    public byte[] exportXls() throws JRException, FileNotFoundException;
}
