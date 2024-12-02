package com.integrador.proyecto_integrador.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.proyecto_integrador.model.Administrador;
import com.integrador.proyecto_integrador.model.IAdministradorDAO;

@Service
public class AdministradorService implements IAdministadorService{

    @Autowired
    private IAdministradorDAO administradorDAO;

    @Override
    public String guardarAdmin(Administrador administrador) {
        try 
        {
            System.out.println("Administrador a guardar: " + administrador.getId_admin()); 
            if (administrador.getId_admin() != null) 
            {
                administradorDAO.save(administrador);
                return "Administrador actualizado o añadido correctamente";
            } else 
            {
                return "No se agregó el cliente: el DNI no puede ser nulo o vacío";
            }
        } catch (Exception e) {
            System.err.println("Error al guardar administrador: " + e.getMessage());
            return "Error al guardar usuario: " + e.getMessage();
        }
    }

    @Override
    public Administrador buscarAdministrador(String id) {
        return administradorDAO.findById(id).get();
    }
    
}
