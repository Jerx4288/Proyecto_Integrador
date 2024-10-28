package com.integrador.proyecto_integrador.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Usuario {
    @Id
    @Column(name = "dni", unique = true, nullable = false)
    private String dni;
    @Column(name = "nombre_c", unique = true, nullable = false)
    private String nombre;
    @Column(name = "apellido_c", unique = true, nullable = false)
    private String apellido;
    @Column(name = "correo_c", unique = true, nullable = false)
    private String correo;
    @Column(name = "dni", unique = true, nullable = false)

    private String password;

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    
}
