package com.integrador.proyecto_integrador.model.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.Boleta;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class boletaReporteGenerator {
    

public byte[] exportToPdf(List<Boleta> list) throws JRException, FileNotFoundException {
    JasperPrint report = getReport(list);
    return JasperExportManager.exportReportToPdf(report);
}

public byte[] exportToXls(List<Boleta> list) throws JRException, FileNotFoundException {
    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
    SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
    JRXlsExporter exporter = new JRXlsExporter();
    exporter.setExporterInput(new SimpleExporterInput(getReport(list)));
    exporter.setExporterOutput(output);
    exporter.exportReport();
    output.close();
    return byteArray.toByteArray();
}

private JasperPrint getReport(List<Boleta> list) throws JRException {
    // Se obtiene el archivo JRXML desde el classpath de la aplicación
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Boleta_por_fecha.jrxml");
    if (inputStream == null) {
        throw new JRException("No se encontró el archivo JRXML.");
    }

    Map<String, Object> params = new HashMap<>();
    params.put("boletasData", new JRBeanCollectionDataSource(list));

    // Llenamos el reporte con los datos
    JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
    JasperPrint report = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

    if (list == null || list.isEmpty()) {
        throw new JRException("No hay datos disponibles para generar el reporte.");
    }

    return report;
}

}
