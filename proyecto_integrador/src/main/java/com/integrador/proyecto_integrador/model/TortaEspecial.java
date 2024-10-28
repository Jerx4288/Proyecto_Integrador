package com.integrador.proyecto_integrador.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "torta_especial")
public class TortaEspecial implements Serializable{
    
    @Id
    @Column(name = "id_tortae")
    private String id_tortae;

    @Column(name = "nombre_te")
    private String nombre_te;

    @Column(name = "precio_te")
    private Double precio_te;

    @Column(name = "dedicatoria_te")
    private String dedicatoria_te;

    @Column(name = "porcion_te")
    private Integer porcion_te;

    @Column(name = "relleno_te")
    private String relleno_te;

    public String getId_tortae() {
        return id_tortae;
    }

    public void setId_tortae(String id_tortae) {
        this.id_tortae = id_tortae;
    }

    public String getNombre_te() {
        return nombre_te;
    }

    public void setNombre_te(String nombre_te) {
        this.nombre_te = nombre_te;
    }

    public Double getPrecio_te() {
        return precio_te;
    }

    public void setPrecio_te(Double precio_te) {
        this.precio_te = precio_te;
    }

    public String getDedicatoria_te() {
        return dedicatoria_te;
    }

    public void setDedicatoria_te(String dedicatoria_te) {
        this.dedicatoria_te = dedicatoria_te;
    }

    public Integer getPorcion_te() {
        return porcion_te;
    }

    public void setPorcion_te(Integer porcion_te) {
        this.porcion_te = porcion_te;
    }

    public String getRelleno_te() {
        return relleno_te;
    }

    public void setRelleno_te(String relleno_te) {
        this.relleno_te = relleno_te;
    }

    public String getTipo_keke_te() {
        return tipo_keke_te;
    }

    public void setTipo_keke_te(String tipo_keke_te) {
        this.tipo_keke_te = tipo_keke_te;
    }

    @Column(name = "tipo_keke_te")
    private String tipo_keke_te;

    
}
