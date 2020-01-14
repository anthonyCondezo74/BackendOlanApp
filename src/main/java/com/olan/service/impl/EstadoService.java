package com.olan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olan.model.Estado;
import com.olan.repo.IEstadoRepo;
import com.olan.service.IEstadoService;

@Service
public class EstadoService implements IEstadoService {

	@Autowired
	private IEstadoRepo repo;
	
	
	@Override
	public Estado registrar(Estado obj) {
		return repo.save(obj);
	}

	@Override
	public void modificar(Estado obj) {
		repo.save(obj);
		
	}

	@Override
	public List<Estado> listar() {
		return repo.findAll();
	}

	@Override
	public Estado leerporId(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
	}

}
