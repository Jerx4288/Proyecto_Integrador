package com.integrador.proyecto_integrador.model.service;

import com.integrador.proyecto_integrador.model.Cliente;

public interface IClienteService {
    public String guardarCliente(Cliente cliente);
    public String iniciarSesion(String dni, String password);
    public String iniciarSesionAdmin(String dni, String password);
}
