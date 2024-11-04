package com.integrador.proyecto_integrador.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrador")
public class Administrador implements Serializable {
    
    @Id
    @Column(name = "id_admin")
    private String id_admin;

    @Column(name = "nombre_a")
    private String nombre_a;

    @Column(name = "apellido_a")
    private String apellido_a;

    @Column(name = "correo_a")
    private String correo_a;

    @Column(name = "password_a")
    private String password_a;

    public String getId_admin() {
        return id_admin;
    }

    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
    }

    public String getNombre_a() {
        return nombre_a;
    }

    public void setNombre_a(String nombre_a) {
        this.nombre_a = nombre_a;
    }

    public String getApellido_a() {
        return apellido_a;
    }

    public void setApellido_a(String apellido_a) {
        this.apellido_a = apellido_a;
    }

    public String getCorreo_a() {
        return correo_a;
    }

    public void setCorreo_a(String correo_a) {
        this.correo_a = correo_a;
    }

    public String getPassword_a() {
        return password_a;
    }

    public void setPassword_a(String password_a) {
        this.password_a = password_a;
    }

    
}
