package com.olan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olan.model.TipoCliente;
import com.olan.repo.ITipoClienteRepo;
import com.olan.service.ITipoClienteService;

@Service
public class TipoClienteService implements ITipoClienteService {

	@Autowired
	private ITipoClienteRepo repo;
	
	
	@Override
	public TipoCliente registrar(TipoCliente obj) {
		return repo.save(obj);
	}

	@Override
	public void modificar(TipoCliente obj) {
		repo.save(obj);
		
	}

	@Override
	public List<TipoCliente> listar() {
		return repo.findAll();
	}

	@Override
	public TipoCliente leerporId(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
	}

}
