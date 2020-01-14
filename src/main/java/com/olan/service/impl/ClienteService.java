package com.olan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olan.model.Cliente;
import com.olan.repo.IClienteRepo;
import com.olan.service.IClienteService;

@Service
public class ClienteService implements IClienteService{

	@Autowired
	private IClienteRepo repo;
	
	
	@Override
	public Cliente registrar(Cliente obj) {
		return repo.save(obj);
	}

	@Override
	public void modificar(Cliente obj) {
		repo.save(obj);
		
	}

	@Override
	public List<Cliente> listar() {
		return repo.findAll();
	}

	@Override
	public Cliente leerporId(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
	}

}
