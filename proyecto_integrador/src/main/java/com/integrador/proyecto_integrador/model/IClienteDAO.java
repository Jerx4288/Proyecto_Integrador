package com.integrador.proyecto_integrador.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDAO extends CrudRepository<Cliente,String> 
{

    @Query("SELECT c.dni FROM Cliente c")  // Consulta JPQL que selecciona solo los DNIs
    List<String> findAllDnis();  // Retorna una lista de String con los DNIs de todos los clientes
}