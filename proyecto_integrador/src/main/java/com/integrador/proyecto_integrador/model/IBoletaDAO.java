package com.integrador.proyecto_integrador.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IBoletaDAO extends CrudRepository<Boleta, Integer>
{
    @Query(value = "SELECT * FROM boleta WHERE cliente_dni like ?1", nativeQuery = true) //consulta de base de datos segun el param
    public List<Boleta> cualquierNombre(String param);

    @Query(value = "SELECT * FROM boleta WHERE cliente_dni = :dni AND MONTH(fecha) = :mes AND YEAR(fecha) = :anio", nativeQuery = true)
    List<Boleta> findByDniAndMesYAnio(@Param("dni") String dni, @Param("mes") Integer mes, @Param("anio") Integer anio);


    @Query("SELECT b FROM Boleta b WHERE MONTH(b.fecha_boleta) = :mes AND YEAR(b.fecha_boleta) = :anio AND b.cliente.dni = :dni")
    List<Boleta> findByMesAndAnioAndClienteDni(@Param("mes") Integer mes, @Param("anio") Integer anio, @Param("dni") String dni);

    @Query("SELECT b FROM Boleta b WHERE MONTH(b.fecha_boleta) = :mes AND YEAR(b.fecha_boleta) = :anio")
    List<Boleta> findByMesAndAnio(@Param("mes") Integer mes, @Param("anio") Integer anio);

    List<Boleta> findByClienteDni(String dniCliente);
    
    @Query("SELECT DISTINCT b.cliente.dni FROM Boleta b")
    List<String> findDistinctDniClientes();

    @Query("SELECT b FROM Boleta b WHERE YEAR(b.fecha_boleta) = :anio AND b.cliente.dni = :dni")
    List<Boleta> findByAnioAndClienteDni(@Param("anio") Integer anio, @Param("dni") String dni);

}
