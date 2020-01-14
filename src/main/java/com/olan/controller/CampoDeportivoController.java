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
import com.olan.model.CampoDeportivo;
import com.olan.service.ICampoDeportivoService;





@RestController
@RequestMapping("/campoDeportivos")
public class CampoDeportivoController {
	
	@Autowired
	private ICampoDeportivoService service;
	
	@GetMapping
	public ResponseEntity<List<CampoDeportivo>> listar(){
		List<CampoDeportivo> listaCampoDeportivo = service.listar();
		return new ResponseEntity<List<CampoDeportivo>>(listaCampoDeportivo, HttpStatus.OK);
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<CampoDeportivo> leerPorId(@PathVariable("id") Integer id) {
		CampoDeportivo objCampoDeportivo =service.leerporId(id);
		if(objCampoDeportivo==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		
		return new ResponseEntity<CampoDeportivo>(objCampoDeportivo,HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public Resource<CampoDeportivo> leerPorIdHateoas(@PathVariable("id") Integer id) {
		CampoDeportivo objCampoDeportivo =service.leerporId(id);
		if(objCampoDeportivo==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		
		Resource<CampoDeportivo> resource = new Resource<CampoDeportivo>(objCampoDeportivo);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
		resource.add(linkTo.withRel("CampoDeportivo-resource"));	
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody CampoDeportivo obj) {
		CampoDeportivo  CampoDeportivo= service.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(CampoDeportivo.getIdCampoDeportivo()).toUri();
		return ResponseEntity.created(location).build();//  <Object>(HttpStatus.CREATED);
	}	
	@PutMapping
	public ResponseEntity<Object> modificar(@RequestBody CampoDeportivo pac) {
		service.modificar(pac);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>  eliminar(@PathVariable("id") Integer id) {
		CampoDeportivo objCampoDeportivo =service.leerporId(id);
		if(objCampoDeportivo==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}else {
			service.eliminar(id);
		}
			
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}

