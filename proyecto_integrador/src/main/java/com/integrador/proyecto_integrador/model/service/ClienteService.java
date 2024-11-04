package com.integrador.proyecto_integrador.model.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.Administrador;
import com.integrador.proyecto_integrador.model.Cliente;
import com.integrador.proyecto_integrador.model.IAdministradorDAO;
import com.integrador.proyecto_integrador.model.IClienteDAO;

import jakarta.transaction.Transactional;


@Service
public class ClienteService implements IClienteService
{

    @Autowired
    private IClienteDAO clienteDAO;
    @Autowired
    private IAdministradorDAO administradorDAO;

    @Override
    @Transactional
    public String guardarCliente(Cliente cliente) {
        try 
        {
            System.out.println("Cliente a guardar: " + cliente.getDni()); 
            if (cliente.getDni() != null) 
            {
                clienteDAO.save(cliente);
                return "Cliente agregado correctamente";
            } else 
            {
                return "No se agregó el cliente: el DNI no puede ser nulo o vacío";
            }
        } catch (Exception e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
            return "Error al guardar usuario: " + e.getMessage();
        }
    }

    @Override
    public String iniciarSesion(String dni, String password) {
        try {
            Optional<Cliente> clienteEn = clienteDAO.findById(dni);
            
            if (clienteEn.isPresent()) {
                Cliente cliente = clienteEn.get();
                if (cliente.getPassword().equals(password)) {
                    String cli_usu = cliente.getNombre() + " "+ cliente.getApellido();
                    System.out.println("Inicio de sesión exitoso para " + cliente.getNombre()); 
                    return cli_usu;
                   
                } else {
                    return "DNI o contraseña incorrectos.";
                }
            } else {
                return "DNI no encontrado.";
            }
        } catch (Exception e) {
            return "Error al iniciar sesión: " + e.getMessage();
        }
    }

    @Override
    public String iniciarSesionAdmin(String dni, String password) {
        String error;
        try {
            Optional<Administrador> administradorEn = administradorDAO.findById(dni);
            
            if (administradorEn.isPresent()) {
                Administrador administrador = administradorEn.get();
                if (administrador.getPassword_a().equals(password)) {
                    error = administrador.getNombre_a() + " " + administrador.getApellido_a();
                    System.out.println("Inicio de sesión exitoso para " + administrador.getNombre_a());
                    return error;
                } else 
                { 
                    error = "DNI o contraseña incorrectos.";
                    return error;
                }
            } else {
                error = "DNI no encontrado.";
                return error;
            }
        } catch (Exception e) {
            error = "Error al iniciar sesión: ";
            return error + e.getMessage();
        }
    }
    

    
}
