package com.integrador.proyecto_integrador.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ICitasDAO extends CrudRepository <Citas, Integer>{
    @Query(value = "SELECT * FROM citas WHERE cliente_dni like ?1", nativeQuery = true) //consulta de base de datos segun el param
    public List<Citas> cualquierNombre(String param);

    @Query(value = "SELECT * FROM citas WHERE cliente_dni = :dni AND MONTH(fecha_c) = :mes AND YEAR(fecha_c) = :anio", nativeQuery = true)
    List<Citas> findByDniAndMesYAnio(@Param("dni") String dni, @Param("mes") Integer mes, @Param("anio") Integer anio);

    @Query("SELECT c FROM Citas c WHERE FUNCTION('MONTH', c.fecha_c) = :mes AND FUNCTION('YEAR', c.fecha_c) = :anio")
    List<Citas> findByMesAndAnio(@Param("mes") Integer mes, @Param("anio") Integer anio);


    @Query("SELECT c FROM Citas c WHERE YEAR(c.fecha_c) = :anio")
    List<Citas> findByAnio(@Param("anio") Integer anio);    

    @Query("SELECT c FROM Citas c WHERE c.cliente.dni = :dni")
    List<Citas> findByClienteDni(@Param("dni") String dni);

    @Query("SELECT DISTINCT c.cliente.dni FROM Citas c")
    List<String> findDistinctDniClientes();
    
    @Query("SELECT c FROM Citas c WHERE YEAR(c.fecha_c) = :anio AND c.cliente.dni = :dni")
    List<Citas> findByAnioAndClienteDni(@Param("anio") Integer anio, @Param("dni") String dni);

    @Query("SELECT c FROM Citas c WHERE MONTH(c.fecha_c) = :mes AND YEAR(c.fecha_c) = :anio AND c.cliente.dni = :dni")
    List<Citas> findByMesAndAnioAndClienteDni(@Param("mes") Integer mes, @Param("anio") Integer anio, @Param("dni") String dni);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Citas c WHERE c.fecha_c = :fecha AND c.hora_c = :hora")
    boolean existsByFechaAndHora(@Param("fecha") LocalDate fecha, @Param("hora") LocalTime hora);
}
