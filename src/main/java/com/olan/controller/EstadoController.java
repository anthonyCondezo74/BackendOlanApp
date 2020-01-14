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
import com.olan.model.Estado;
import com.olan.service.IEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private IEstadoService service;
	
	@GetMapping
	public ResponseEntity<List<Estado>> listar(){
		List<Estado> listaEstado = service.listar();
		return new ResponseEntity<List<Estado>>(listaEstado, HttpStatus.OK);
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> leerPorId(@PathVariable("id") Integer id) {
		Estado objEstado =service.leerporId(id);
		if(objEstado==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		
		return new ResponseEntity<Estado>(objEstado,HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public Resource<Estado> leerPorIdHateoas(@PathVariable("id") Integer id) {
		Estado objEstado =service.leerporId(id);
		if(objEstado==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		
		Resource<Estado> resource = new Resource<Estado>(objEstado);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
		resource.add(linkTo.withRel("Estado-resource"));	
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Estado obj) {
		Estado  Estado= service.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Estado.getIdEstado()).toUri();
		return ResponseEntity.created(location).build();//  <Object>(HttpStatus.CREATED);
	}	
	@PutMapping
	public ResponseEntity<Object> modificar(@RequestBody Estado pac) {
		service.modificar(pac);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>  eliminar(@PathVariable("id") Integer id) {
		Estado objEstado =service.leerporId(id);
		if(objEstado==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}else {
			service.eliminar(id);
		}
			
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}

