package com.olan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olan.model.CampoDeportivo;
import com.olan.repo.ICampoDeportivoRepo;
import com.olan.service.ICampoDeportivoService;

@Service
public class CampoDeportivoService implements ICampoDeportivoService {

	@Autowired
	private ICampoDeportivoRepo repo;
	
	@Override
	public CampoDeportivo registrar(CampoDeportivo obj) {
		return repo.save(obj);
	}

	@Override
	public void modificar(CampoDeportivo obj) {
		repo.save(obj);
		
	}

	@Override
	public List<CampoDeportivo> listar() {
		return repo.findAll();
	}

	@Override
	public CampoDeportivo leerporId(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
		
	}



}
