package com.integrador.proyecto_integrador.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "cliente")
public class  Cliente implements Serializable {

    @Id
    @Column(name = "dni")
    private String dni;

    @Column(name = "nombre_c")
    private String nombre;

    @Column(name = "apellido_c")
    private String apellido;

    @Column(name = "correo_c")
    private String correo;

    @Column(name =  "password_c")
    private String password;

    @Column(name = "telefono_c")
    private String telefono;

    @Column(name = "direccion_c")
    private String direccion;

    @Column(name = "distrito_c")
    private String distrito;

    @Transient // Esto indica que no se debe mapear a una columna de la base de datos
    private String rol;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    @OneToMany(mappedBy = "cliente")
    private List<Boleta> boletas;
        

}
