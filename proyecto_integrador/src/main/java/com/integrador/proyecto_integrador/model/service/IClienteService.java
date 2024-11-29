package com.integrador.proyecto_integrador.model.service;

import java.util.Optional;

import com.integrador.proyecto_integrador.model.Administrador;
import com.integrador.proyecto_integrador.model.Cliente;

public interface IClienteService {
    public String guardarCliente(Cliente cliente);
    public Optional<Cliente> iniciarSesion(String dni, String password);
    public Optional<Administrador> iniciarSesionAdmin(String dni, String password);

    public Cliente buscarCliente(String id);
}
