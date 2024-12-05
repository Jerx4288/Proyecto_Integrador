package com.integrador.proyecto_integrador.model.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.Boleta;
import com.integrador.proyecto_integrador.model.IBoletaDAO;
import com.integrador.proyecto_integrador.model.util.boletaReporteGenerator;
import com.integrador.proyecto_integrador.model.util.boletaReporteMaximoGenerator;

import net.sf.jasperreports.engine.JRException;

@Service
public class BoletaService implements IBoletaService{
    
    @Autowired
    IBoletaDAO boletaDAO;

    @Autowired
    private boletaReporteGenerator boletaReporteGenerator;

    @Autowired
    private boletaReporteMaximoGenerator boletaReporteMaximoGenerator;

    @Override
    public String guardarBoleta(Boleta boleta) {
        boletaDAO.save(boleta);
        return "redirect:/boleta";
    }

    @Override
    public List<Boleta> cargarCategoriasFiltradas(String param) {
        return boletaDAO.cualquierNombre(param);
    }

    @Override
    public List<Boleta> filtrarPorMesYAnio(String dni, Integer mes, Integer anio) 
    {
        return boletaDAO.findByDniAndMesYAnio(dni, mes, anio);
    }

    @Override
    public List<Boleta> obtenerBoletasPorMesAnioYCliente(Integer mes, Integer anio, String dniCliente) {
        return boletaDAO.findByMesAndAnioAndClienteDni(mes, anio, dniCliente);
    }    

    @Override
    public List<Boleta> obtenerBoletasPorMesAnio(Integer mes, Integer anio) {
        return boletaDAO.findByMesAndAnio(mes, anio);
    }

    @Override
    public List<Boleta> obtenerBoletasPorCliente(String dniCliente) {
        return boletaDAO.findByClienteDni(dniCliente);
    }

    @Override
    public List<Boleta> obtenerTodasBoletas() {
        return (List<Boleta>) boletaDAO.findAll();
    }

    @Override
    public List<String> obtenerDnisClientes() {
        return boletaDAO.findDistinctDniClientes(); 
    }

    @Override
    public List<Boleta> obtenerBoletasPorAnioYCliente(Integer anio, String dniCliente) {
        return boletaDAO.findByAnioAndClienteDni(anio, dniCliente);
    }

    @Override
    public byte[] exportPDF(Integer mes, Integer anio) throws JRException, FileNotFoundException {
        List<Boleta> boletas = boletaDAO.findByMesAndAnio(mes, anio);
        System.out.println("Boletas para reporte: " + boletas);

        if (boletas.isEmpty()) {
            throw new JRException("No hay datos para generar el reporte en formato PDF.");
        }
        return boletaReporteGenerator.exportToPdf(boletas);
    }

    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exportXls'");
    }

    @Override
    public byte[] exportPDF() throws JRException, FileNotFoundException {
        List<Boleta> boletas = new ArrayList<>();
        boletaDAO.findAll().forEach(boletas::add);
        System.out.println("Tortas para reporte: " + boletas);

        if (boletas.isEmpty()) {
            throw new JRException("No hay datos para generar el reporte en formato PDF.");
        }

        return boletaReporteMaximoGenerator.exportToPdf(boletas);
    }
    
}
