package com.integrador.proyecto_integrador.model.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.integrador.proyecto_integrador.model.TortaEspecial;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class tortaEsReporteGeneratos {
    public byte[] exportToPdf(List<TortaEspecial> list) throws JRException, FileNotFoundException 
     {
        return JasperExportManager.exportReportToPdf(getReport(list));
    }
    public byte[] exportToXls(List<TortaEspecial> list) throws JRException, FileNotFoundException 
    {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getReport(list)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getReport(List<TortaEspecial> list) throws FileNotFoundException, JRException 
    {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tortasEsData", new JRBeanCollectionDataSource(list));

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:Stock_tortaEspecial.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());
                        if (list == null || list.isEmpty()) {
                            throw new JRException("No hay datos disponibles para generar el reporte.");
                        }
        return report;
    }
}
