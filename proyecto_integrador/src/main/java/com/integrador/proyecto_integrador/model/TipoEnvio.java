package com.integrador.proyecto_integrador.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "entrega_producto")
public class TipoEnvio {
    
    @Id
    @Column(name = "id_entrega")
    private Integer id_tipen;

    @Column(name = "tipo_entrega")
    private String tipo_e; // Es 'tipo_e', no 'nombre'

    @OneToMany(mappedBy = "tipoenvio")
    private List<Boleta> boletas;

    @Override
    public String toString() {
        return "TipoEnvio{" +
               "idTipoEnvio=" + id_tipen +
               ", tipo_e='" + tipo_e + '\'' + // Asegúrate de usar 'tipo_e'
               '}';
    }

    // Getters y setters
    public Integer getId_tipen() {
        return id_tipen;
    }

    public void setId_tipen(Integer id_tipen) {
        this.id_tipen = id_tipen;
    }

    public String getTipo_e() {
        return tipo_e;
    }

    public void setTipo_e(String tipo_e) {
        this.tipo_e = tipo_e;
    }
}
