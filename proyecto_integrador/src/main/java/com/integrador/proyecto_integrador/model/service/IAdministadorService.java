package com.integrador.proyecto_integrador.model.service;

import com.integrador.proyecto_integrador.model.Administrador;


public interface IAdministadorService {
    public String guardarAdmin(Administrador administrador);
    public Administrador buscarAdministrador(String id);
}
