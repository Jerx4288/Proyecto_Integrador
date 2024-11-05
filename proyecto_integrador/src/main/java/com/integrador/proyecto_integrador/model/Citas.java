package com.integrador.proyecto_integrador.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Blob;

@Entity
@Table(name = "citas")
public class Citas {
    
    @Id
    @Column(name = "id_citas")
    private String id;
    
    @Column(name = "nombre_c")
    private String nombre_c;

    @Column(name = "correo_c")
    private String correo_c;

    @Column(name = "celular_c")
    private String celular_c;

    @Column(name = "fecha_c")
    private String fecha_c;

    @Column(name = "hora_c")
    private String hora_c;

    @Column(name = "imagen_c")
    private Blob imagen_c;

    @ManyToOne
    @JoinColumn(name = "cliente_dni")
    Cliente cliente;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getFecha_c() {
        return fecha_c;
    }

    public void setFecha_c(String fecha_c) {
        this.fecha_c = fecha_c;
    }

    public String getHora_c() {
        return hora_c;
    }

    public void setHora_c(String hora_c) {
        this.hora_c = hora_c;
    }

    public Blob getImagen_c() {
        return imagen_c;
    }

    public void setImagen_c(Blob imagen_c) {
        this.imagen_c = imagen_c;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
