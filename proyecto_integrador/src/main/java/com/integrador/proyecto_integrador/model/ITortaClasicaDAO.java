package com.integrador.proyecto_integrador.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ITortaClasicaDAO extends CrudRepository<TortaClasica,String> 
{
        @Query("SELECT t FROM TortaClasica t WHERE t.nombre_tc = :nombre AND t.tamano_tc = :tamano")
        List<TortaClasica> buscarPorNombreYTamano(@Param("nombre") String nombre, @Param("tamano") String tamano);
} 