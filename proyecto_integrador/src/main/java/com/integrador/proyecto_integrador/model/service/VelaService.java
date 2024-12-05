package com.integrador.proyecto_integrador.model.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.IVelaDAO;
import com.integrador.proyecto_integrador.model.Vela;
import com.integrador.proyecto_integrador.model.util.velaReporteGenerator;

import net.sf.jasperreports.engine.JRException;

@Service
public class VelaService implements IVelaService {

    @Autowired
    private IVelaDAO velaDAO;

    @Autowired
    private velaReporteGenerator velaReporteGenerator;
    @Override
    public List<Vela> cargarVelas(List<String> ids) {
       System.out.println("ID VELAS RECIBIDO: " + ids);
       return velaDAO.findByIdsIn(ids);
    }
    @Override
    public List<Vela> cargarCategoriasFiltradas(String nombre) {
        return velaDAO.buscarPorNombre(nombre);
    }
    @Override
    public void guardarVela(Vela vela) {
        velaDAO.save(vela);
    }
    @Override
    public List<Vela> cargaTodasVelas() {
        return (List<Vela>) velaDAO.findAll();
    }
    @Override
    public Vela buscarVela(String id) {
        return velaDAO.findById(id).get();
    }
    @Override
    public byte[] exportPDF() throws JRException, FileNotFoundException {
        List<Vela> tortas = new ArrayList<>();
        velaDAO.findAll().forEach(tortas::add);
        System.out.println("Tortas para reporte: " + tortas);

        if (tortas.isEmpty()) {
            throw new JRException("No hay datos para generar el reporte en formato PDF.");
        }

        return velaReporteGenerator.exportToPdf(tortas);
    }
    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        List<Vela> tortas = new ArrayList<>();
        velaDAO.findAll().forEach(tortas::add);

        if (tortas.isEmpty()) {
            throw new JRException("No hay datos para generar el reporte en formato XLS.");
        }

        return velaReporteGenerator.exportToXls(tortas);
    }
    
}
