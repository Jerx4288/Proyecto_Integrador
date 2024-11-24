package com.integrador.proyecto_integrador.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    private double precio_te;

    @Column(name = "porcion_te")
    private String porcion_te;

    @Column(name = "relleno_te")
    private String relleno_te;

    @Column(name = "tipo_keke_te")
    private String tipo_keke_te;

    @Column(name = "stock_te")
    private String stock_te;

    @Override
    public String toString() {
        return "TortaEspecial{" +
               "id_tortae='" + id_tortae + '\'' +
               ", nombre_te='" + nombre_te + '\'' +
               ", precio_te='" + precio_te + '\'' +
               ", porcion_te='" + porcion_te + '\'' +
               ", relleno_te='" + relleno_te + '\'' +
               ", tipo_keke_te='" + tipo_keke_te + '\'' +
               ", stock_te='" + stock_te + '\'' +
               '}';
    }
    @ManyToMany(mappedBy = "tortaEspeciales")
    private List<Boleta> boletas = new ArrayList<>();

    public TortaEspecial() {
    }

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

    public String getPorcion_te() {
        return porcion_te;
    }

    public void setPorcion_te(String porcion_te) {
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

    public String getStock_te() {
        return stock_te;
    }

    public void setStock_te(String stock_te) {
        this.stock_te = stock_te;
    }

    public List<Boleta> getBoletas() {
        return boletas;
    }

    public void setBoletas(List<Boleta> boletas) {
        this.boletas = boletas;
    }

    
    
}
