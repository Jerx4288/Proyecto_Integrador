package com.integrador.proyecto_integrador.model.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.ITortaClasicaDAO;
import com.integrador.proyecto_integrador.model.TortaClasica;
import com.integrador.proyecto_integrador.model.util.tortaClaReporteGenerator;

import net.sf.jasperreports.engine.JRException;

@Service
public class TortaClaService implements ITortaClaService {


    @Autowired
    private ITortaClasicaDAO tortaClasicaDAO;

    @Autowired
    private tortaClaReporteGenerator tortaClaReporteGenerator;

    @Override
    public List<TortaClasica> cargarCategoriasFiltradas(String nombre, String tamano) {
        return tortaClasicaDAO.buscarPorNombreYTamano(nombre, tamano);
    }

    @Override
    public List<TortaClasica> cargarTortaClasicas(List<String> ids) {
        System.out.println("IDs recibidos: " + ids);  // Agregar esta línea para ver los IDs
        return tortaClasicaDAO.findByIdsIn(ids);
    }

    @Override
    public void guardarTortaClasica(TortaClasica tortaClasica) {
        tortaClasicaDAO.save(tortaClasica);
    }

    @Override
    public List<TortaClasica> cargarTodasTortas() {
        return (List<TortaClasica>) tortaClasicaDAO.findAll();
    }

    @Override
    public TortaClasica buscarTortaClasica(String id) {
        return tortaClasicaDAO.findById(id).get();
    }

    @Override
    public byte[] exportPDF() throws JRException, FileNotFoundException {
        List<TortaClasica> tortas = new ArrayList<>();
        tortaClasicaDAO.findAll().forEach(tortas::add);
        System.out.println("Tortas para reporte: " + tortas);

        if (tortas.isEmpty()) {
            throw new JRException("No hay datos para generar el reporte en formato PDF.");
        }

        return tortaClaReporteGenerator.exportToPdf(tortas);
    }

    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        List<TortaClasica> tortas = new ArrayList<>();
        tortaClasicaDAO.findAll().forEach(tortas::add);

        if (tortas.isEmpty()) {
            throw new JRException("No hay datos para generar el reporte en formato XLS.");
        }

        return tortaClaReporteGenerator.exportToXls(tortas);
    }

    



    
    
}
