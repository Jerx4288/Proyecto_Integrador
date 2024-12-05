package com.integrador.proyecto_integrador.model.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.ITortaEspecialDAO;
import com.integrador.proyecto_integrador.model.TortaEspecial;
import com.integrador.proyecto_integrador.model.util.tortaEsReporteGeneratos;

import net.sf.jasperreports.engine.JRException;

@Service
public class TortaEspecialService implements ITortaEspecialService 
{

    @Autowired
    private ITortaEspecialDAO tortaEspecialDAO;

    @Autowired
    private tortaEsReporteGeneratos tortaEsReporteGeneratos;

    @Override
    public List<TortaEspecial> cargarCategoriasFiltradas(String nombre, String tamano, String relleno,String tipo_keke) 
    {
        
        return tortaEspecialDAO.buscarPorNombreYTamano(nombre, tamano, relleno,tipo_keke);
    }

    @Override
    public List<TortaEspecial> cargarTortaEspecial(List<String> ids) {
        System.out.println("IDs recibidos: " + ids);  // Agregar esta línea para ver los IDs
        return tortaEspecialDAO.findByIdsIn(ids);
    }

    @Override
    public void guardarTortaEspecial(TortaEspecial tortaEspecial) {
        tortaEspecialDAO.save(tortaEspecial);
    }

    @Override
    public List<TortaEspecial> cargarTodastort() {
        return (List<TortaEspecial>) tortaEspecialDAO.findAll();
    }

    @Override
    public TortaEspecial buscarTortaEspecial(String id) {
        return tortaEspecialDAO.findById(id).get();
    }

    @Override
    public byte[] exportPDF() throws JRException, FileNotFoundException {
        List<TortaEspecial> tortas = new ArrayList<>();
        tortaEspecialDAO.findAll().forEach(tortas::add);
        System.out.println("Tortas para reporte: " + tortas);

        if (tortas.isEmpty()) {
            throw new JRException("No hay datos para generar el reporte en formato PDF.");
        }

        return tortaEsReporteGeneratos.exportToPdf(tortas);
    }

    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        List<TortaEspecial> tortas = new ArrayList<>();
        tortaEspecialDAO.findAll().forEach(tortas::add);

        if (tortas.isEmpty()) {
            throw new JRException("No hay datos para generar el reporte en formato XLS.");
        }

        return tortaEsReporteGeneratos.exportToXls(tortas);
    }
    
}
