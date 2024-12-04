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
@Table(name = "torta_clasica")
public class TortaClasica implements Serializable{
    
    @Id
    @Column(name = "id_tortac")
    private String id_tortac;

    @Column(name = "nombre_tc")
    private String nombre_tc;

    @Column(name = "precio_tc")
    private double precio_tc;

    @Column(name = "tamano_tc")
    private String tamano_tc;

    @Column(name = "stock_tc")
    private Integer stock_tc;

    public String getId_tortac() {
        return id_tortac;
    }

    public void setId_tortac(String id_tortac) {
        this.id_tortac = id_tortac;
    }

    public String getNombre_tc() {
        return nombre_tc;
    }

    public void setNombre_tc(String nombre_tc) {
        this.nombre_tc = nombre_tc;
    }

    public double getPrecio_tc() {
        return precio_tc;
    }

    public void setPrecio_tc(double precio_tc) {
        this.precio_tc = precio_tc;
    }

    public String getTamano_tc() {
        return tamano_tc;
    }

    public void setTamano_tc(String tamano_tc) {
        this.tamano_tc = tamano_tc;
    }

    public Integer getStock_tc() {
        return stock_tc;
    }

    public void setStock_tc(Integer stock_tc) {
        this.stock_tc = stock_tc;
    }

    @Override
    public String toString() {
        return "TortaClasica{" +
               "id_tortac='" + id_tortac + '\'' +
               ", nombre_tc='" + nombre_tc + '\'' +
               ", precio_tc='" + precio_tc + '\'' +
               ", tamano_tc='" + tamano_tc + '\'' +
               ", stock_tc='" + stock_tc + '\'' +
               '}';
    }
    
    @ManyToMany(mappedBy = "tortasClasicas")
    private List<Boleta> boletas = new ArrayList<>();
    
    
    public TortaClasica() {
    
    }   
    
    public List<Boleta> getBoletas() {
        return boletas;
    }

    public void setBoletas(List<Boleta> boletas) {
        this.boletas = boletas;
    }
    
    
}
