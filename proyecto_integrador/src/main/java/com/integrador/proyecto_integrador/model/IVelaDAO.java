package com.integrador.proyecto_integrador.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IVelaDAO extends CrudRepository<Vela,String>
{
     @Query("SELECT t FROM Vela t WHERE t.id_vela IN :ids")
        List<Vela> findByIdsIn(@Param("ids") List<String> ids);  
        
        @Query("SELECT t FROM Vela t WHERE t.modelo_vela = :nombre")
        List<Vela> buscarPorNombre(@Param("nombre") String nombre);
        

}
