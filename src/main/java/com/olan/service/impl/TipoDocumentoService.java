package com.olan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olan.model.TipoDocumento;
import com.olan.repo.ITipoDocumentoRepo;
import com.olan.service.ITipoDocumentoService;

@Service
public class TipoDocumentoService implements ITipoDocumentoService {

	@Autowired
	private ITipoDocumentoRepo repo;
	
	
	@Override
	public TipoDocumento registrar(TipoDocumento obj) {
		return repo.save(obj);
	}

	@Override
	public void modificar(TipoDocumento obj) {
		repo.save(obj);
		
	}

	@Override
	public List<TipoDocumento> listar() {
		return repo.findAll();
	}

	@Override
	public TipoDocumento leerporId(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
	}

}
