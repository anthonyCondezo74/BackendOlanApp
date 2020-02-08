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
import com.olan.model.TipoCliente;
import com.olan.service.ITipoClienteService;

@RestController
@RequestMapping("/TipoClientes")
public class TipoClienteController {

	@Autowired
	private ITipoClienteService service;

	@GetMapping
	public ResponseEntity<List<TipoCliente>> listar() {
		List<TipoCliente> listaTipoCliente = service.listar();
		return new ResponseEntity<List<TipoCliente>>(listaTipoCliente, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoCliente> leerPorId(@PathVariable("id") Integer id) {
		TipoCliente objTipoCliente = service.leerporId(id);
		if (objTipoCliente == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
		}

		return new ResponseEntity<TipoCliente>(objTipoCliente, HttpStatus.OK);
	}

	@GetMapping("/hateoas/{id}")
	public Resource<TipoCliente> leerPorIdHateoas(@PathVariable("id") Integer id) {
		TipoCliente objTipoCliente = service.leerporId(id);
		if (objTipoCliente == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
		}

		Resource<TipoCliente> resource = new Resource<TipoCliente>(objTipoCliente);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
		resource.add(linkTo.withRel("TipoCliente-resource"));

		return resource;
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody TipoCliente obj) {
		TipoCliente TipoCliente = service.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(TipoCliente.getId()).toUri();
		return ResponseEntity.created(location).build();// <Object>(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Object> modificar(@RequestBody TipoCliente pac) {
		service.modificar(pac);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		TipoCliente objTipoCliente = service.leerporId(id);
		if (objTipoCliente == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
		} else {
			service.eliminar(id);
		}

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
