package com.integrador.proyecto_integrador.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ITortaEspecialDAO extends CrudRepository<TortaEspecial, String> {
    
    @Query("SELECT t FROM TortaEspecial t WHERE t.nombre_te = :nombre AND t.porcion_te = :tamano AND t.relleno_te = :relleno AND t.tipo_keke_te = :tipo_keke")
    List<TortaEspecial> buscarPorNombreYTamano(@Param("nombre") String nombre, 
                                            @Param("tamano") String tamano, 
                                            @Param("relleno") String relleno, 
                                            @Param("tipo_keke") String tipoKeke);

     @Query("SELECT t FROM TortaEspecial t WHERE t.id_tortae IN :ids")
        List<TortaEspecial> findByIdsIn(@Param("ids") List<String> ids);
}
