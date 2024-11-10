package com.integrador.proyecto_integrador.model;

public class ProductosCarrito {
    private String nombre;
    private String tamano;
    private int cantidad;

    public ProductosCarrito(String nombre, String tamano, int cantidad) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}
