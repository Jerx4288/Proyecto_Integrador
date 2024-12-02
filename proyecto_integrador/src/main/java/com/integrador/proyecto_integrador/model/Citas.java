package com.integrador.proyecto_integrador.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name = "citas")
public class Citas {
    
    @Id
    @Column(name = "id_citas")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O usa otro strategy adecuado
    private Integer id;

    
    @Column(name = "nombre_c")
    private String nombre_c;

    @Column(name = "correo_c")
    private String correo_c;

    @Column(name = "celular_c")
    private String celular_c;

    @Column(name = "fecha_c")
    private LocalDate fecha_c;

    @Column(name = "hora_c")
    private LocalTime hora_c;

     // Cambiado a MultipartFile
    @Transient
    private MultipartFile imagen_c;  // No es necesario almacenar el archivo en la base de datos, solo su ruta

    @Column(name = "imagen_c") // Ruta del archivo que se guardará en la base de datos
    private String imagen_c_ruta;


    @ManyToOne
    @JoinColumn(name = "cliente_dni")
    Cliente cliente;


    public String getNombre_c() {
        return nombre_c;
    }

    public void setNombre_c(String nombre_c) {
        this.nombre_c = nombre_c;
    }

    public String getCorreo_c() {
        return correo_c;
    }

    public void setCorreo_c(String correo_c) {
        this.correo_c = correo_c;
    }

    public String getCelular_c() {
        return celular_c;
    }

    public void setCelular_c(String celular_c) {
        this.celular_c = celular_c;
    }

    public LocalDate getFecha_c() {
        return fecha_c;
    }

    public void setFecha_c(LocalDate fecha_c) {
        this.fecha_c = fecha_c;
    }

    public LocalTime getHora_c() {
        return hora_c;
    }

    public void setHora_c(LocalTime hora_c) {
        this.hora_c = hora_c;
    }

    public MultipartFile getImagen_c() {
        return imagen_c;
    }

    public void setImagen_c(MultipartFile imagen_c) {
        this.imagen_c = imagen_c;
    }

    public String getImagen_c_ruta() {
        return imagen_c_ruta;
    }

    public void setImagen_c_ruta(String imagen_c_ruta) {
        this.imagen_c_ruta = imagen_c_ruta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
