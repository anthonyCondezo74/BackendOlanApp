package com.olan.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olan.dto.ConsultaResumenDTO;
import com.olan.dto.FiltroReservaDTO;
import com.olan.model.Reserva;
import com.olan.repo.IReservaRepo;
import com.olan.service.IReservaService;

@Service
public class ReservaService implements IReservaService{

	@Autowired
	private IReservaRepo repo;
	
	
	@Override
	public Reserva registrar(Reserva obj) {
		return repo.save(obj);
	}

	@Override
	public void modificar(Reserva obj) {
		repo.save(obj);
		
	}

	@Override
	public List<Reserva> listar() {
		return repo.findAll();
	}

	@Override
	public Reserva leerporId(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
	}

	@Override
	public List<Reserva> buscar(FiltroReservaDTO filtro) {
		return repo.buscar(filtro.getNroDocumento() , filtro.getNombreCompleto());
	}

	@Override
	public List<Reserva> buscarFecha(FiltroReservaDTO filtro) {
		LocalDateTime fechaSfte = filtro.getFechaReserva().plusDays(1);
		return repo.buscarFecha(filtro.getFechaReserva(), fechaSfte );
	}

	@Override
	public List<ConsultaResumenDTO> listarResumen() {
		List<ConsultaResumenDTO> consultas = new ArrayList();
		repo.listarResumen().forEach(x->{
			ConsultaResumenDTO cr = new ConsultaResumenDTO();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consultas.add(cr);
		});
		return consultas;
	}

}
