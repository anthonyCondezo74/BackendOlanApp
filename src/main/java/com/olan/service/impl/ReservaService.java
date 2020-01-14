package com.olan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
