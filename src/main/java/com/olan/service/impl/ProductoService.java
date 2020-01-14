package com.olan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olan.model.Producto;
import com.olan.repo.IProductoRepo;
import com.olan.service.IProductoService;

@Service
public class ProductoService implements IProductoService {

	@Autowired
	private IProductoRepo repo;
	
	
	@Override
	public Producto registrar(Producto obj) {
		return repo.save(obj);
	}

	@Override
	public void modificar(Producto obj) {
		repo.save(obj);
		
	}

	@Override
	public List<Producto> listar() {
		return repo.findAll();
	}

	@Override
	public Producto leerporId(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
	}

}
