package com.integrador.proyecto_integrador.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ITipoEnvioDAO extends CrudRepository<TipoEnvio, Integer>
{
    @Query("SELECT t FROM TipoEnvio t WHERE t.tipo_e = :tipo_e")
    TipoEnvio findByTipo_e(@Param("tipo_e") String tipo_e);
}
