package com.olan.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.olan.exception.ModelNotFoundException;
import com.olan.model.TipoDocumento;
import com.olan.service.ITipoDocumentoService;

@RestController
@RequestMapping("/TipoDocumentos")
public class TipoDocumentoController {

	@Autowired
	private ITipoDocumentoService service;

	@GetMapping
	public ResponseEntity<List<TipoDocumento>> listar() {
		List<TipoDocumento> listaTipoDocumento = service.listar();
		return new ResponseEntity<List<TipoDocumento>>(listaTipoDocumento, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoDocumento> leerPorId(@PathVariable("id") Integer id) {
		TipoDocumento objTipoDocumento = service.leerporId(id);
		if (objTipoDocumento == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
		}

		return new ResponseEntity<TipoDocumento>(objTipoDocumento, HttpStatus.OK);
	}

	@GetMapping("/hateoas/{id}")
	public Resource<TipoDocumento> leerPorIdHateoas(@PathVariable("id") Integer id) {
		TipoDocumento objTipoDocumento = service.leerporId(id);
		if (objTipoDocumento == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
		}

		Resource<TipoDocumento> resource = new Resource<TipoDocumento>(objTipoDocumento);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
		resource.add(linkTo.withRel("TipoDocumento-resource"));

		return resource;
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody TipoDocumento obj) {
		TipoDocumento TipoDocumento = service.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(TipoDocumento.getId()).toUri();
		return ResponseEntity.created(location).build();// <Object>(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Object> modificar(@RequestBody TipoDocumento pac) {
		service.modificar(pac);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		TipoDocumento objTipoDocumento = service.leerporId(id);
		if (objTipoDocumento == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
		} else {
			service.eliminar(id);
		}

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
