package com.integrador.proyecto_integrador.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.Boleta;
import com.integrador.proyecto_integrador.model.IBoletaDAO;

@Service
public class BoletaService implements IBoletaService{
    
    @Autowired
    IBoletaDAO boletaDAO;

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
    
}
