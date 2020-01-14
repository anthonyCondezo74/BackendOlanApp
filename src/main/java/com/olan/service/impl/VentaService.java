package com.olan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olan.model.Venta;
import com.olan.repo.IVentaRepo;
import com.olan.service.IVentaService;

@Service
public class VentaService implements IVentaService {

	@Autowired
	private IVentaRepo repo;
	
	
	@Override
	public Venta registrar(Venta obj) {
		obj.getDetalleVenta().forEach(det ->{
			det.setVenta(obj); 
		});
		
		return repo.save(obj);
	}

	@Override
	public void modificar(Venta obj) {
		repo.save(obj);
		
	}

	@Override
	public List<Venta> listar() {
		return repo.findAll();
	}

	@Override
	public Venta leerporId(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
	}

}
