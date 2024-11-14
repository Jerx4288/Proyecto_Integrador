package com.integrador.proyecto_integrador.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "boleta")
public class Boleta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Sirve como auto incremento y identifica que ID es la clave primaria
    @Column(name = "id_detalle_boleta")
    private Integer id_boleta;

    @Column(name = "fecha")
    private String fecha_boleta;

    @Column(name = "sub_total")
    private Double total_boleta;

    @Column(name = "metodo_pago")
    private String metpago_boleta;

    @Column(name = "dedicatoria")
    private String dedicatoria;

    @Column(name = "cantidad")
    private Integer cantidad_bol;

    public Integer getId_boleta() {
        return id_boleta;
    }

    public void setId_boleta(Integer id_boleta) {
        this.id_boleta = id_boleta;
    }

    public String getFecha_boleta() {
        return fecha_boleta;
    }

    public void setFecha_boleta(String fecha_boleta) {
        this.fecha_boleta = fecha_boleta;
    }

    public Double getTotal_boleta() {
        return total_boleta;
    }

    public void setTotal_boleta(Double total_boleta) {
        this.total_boleta = total_boleta;
    }

    public String getMetpago_boleta() {
        return metpago_boleta;
    }

    public void setMetpago_boleta(String metpago_boleta) {
        this.metpago_boleta = metpago_boleta;
    }

    public String getDedicatoria() {
        return dedicatoria;
    }

    public void setDedicatoria(String dedicatoria) {
        this.dedicatoria = dedicatoria;
    }

    @ManyToMany
    @JoinTable(
        name = "boleta_has_torta_clasica", 
        joinColumns = @JoinColumn(name = "boleta_id_detalle_boleta"), 
        inverseJoinColumns = @JoinColumn(name = "torta_clasica_id_tortac")
    )
    private List<TortaClasica> tortasClasicas= new ArrayList<>();
    public List<TortaClasica> getTortasClasicas() {
        return tortasClasicas;
    }

    public void setTortasClasicas(List<TortaClasica> tortasClasicas) {
        this.tortasClasicas = tortasClasicas;
    }
    @ManyToOne
    @JoinColumn(name = "cliente_dni")  // 'dni' es la clave foránea que referencia a Cliente
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "entrega_producto_id_entrega")
    private TipoEnvio tipoenvio;


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoEnvio getTipoenvio() {
        return tipoenvio;
    }

    public void setTipoenvio(TipoEnvio tipoenvio) {
        this.tipoenvio = tipoenvio;
    }

    public Boleta(Integer id_boleta, String fecha_boleta, double total_boleta, String metpago_boleta,
            String dedicatoria, List<TortaClasica> tortaClasica, Integer cantidad_bol ,Cliente cliente, TipoEnvio tipoenvio) {
        this.id_boleta = id_boleta;
        this.fecha_boleta = fecha_boleta;
        this.total_boleta = total_boleta;
        this.metpago_boleta = metpago_boleta;
        this.dedicatoria = dedicatoria;
        this.tortasClasicas = tortaClasica;
        this.cantidad_bol = cantidad_bol;
        this.cliente = cliente;
        this.tipoenvio = tipoenvio;
    }
    
    public Boleta() {
        // Constructor por defecto para JPA
    }

    public Integer getCantidad_bol() {
        return cantidad_bol;
    }

    public void setCantidad_bol(Integer cantidad_bol) {
        this.cantidad_bol = cantidad_bol;
    }


    
    
}
