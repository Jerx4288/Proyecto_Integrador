package com.integrador.proyecto_integrador.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "entrega_producto")
public class EntregaP {
    
    @Id
    @Column(name = "id_entrega")
    private String id_entrega;

    @Column(name = "tipo_entrega")
    private String tipo_entrega;
}
