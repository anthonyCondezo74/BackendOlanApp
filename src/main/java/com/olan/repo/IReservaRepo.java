package com.olan.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olan.model.Reserva;

public interface IReservaRepo extends JpaRepository<Reserva, Integer> {

	@Query("from Reserva r where r.cliente.nroDocumento =:nroDocumento or LOWER(r.cliente.nombre) like %:nombreCompleto% or LOWER(r.cliente.apellido) like %:nombreCompleto%")
	List<Reserva>buscar(@Param("nroDocumento")int nroDocumento, @Param("nombreCompleto") String nombreCompleto);
	
	@Query("from Reserva r where r.fecha between :fechaConsulta and :fechaSgte")
	List<Reserva> buscarFecha(@Param("fechaConsulta") LocalDateTime fechaReserva, @Param("fechaSgte") LocalDateTime fechaSgte);
	
	@Query(value = "select * from fn_listarResumen()", nativeQuery = true)
	List<Object[]> listarResumen();
}
