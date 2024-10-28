package com.integrador.proyecto_integrador.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vela")
public class Vela implements Serializable{
    @Id
    @Column(name = "id_vela")
    private String id_vela;

    @Column(name = "stock_vela")
    private Integer stock_vela;

    @Column(name = "modelo_vela")
    private String modelo_vela;

    public String getId_vela() {
        return id_vela;
    }

    public void setId_vela(String id_vela) {
        this.id_vela = id_vela;
    }

    public Integer getStock_vela() {
        return stock_vela;
    }

    public void setStock_vela(Integer stock_vela) {
        this.stock_vela = stock_vela;
    }

    public String getModelo_vela() {
        return modelo_vela;
    }

    public void setModelo_vela(String modelo_vela) {
        this.modelo_vela = modelo_vela;
    }

    public Double getPrecio_vela() {
        return precio_vela;
    }

    public void setPrecio_vela(Double precio_vela) {
        this.precio_vela = precio_vela;
    }

    @Column(name = "precio_vela")
    private Double precio_vela;
    
}
