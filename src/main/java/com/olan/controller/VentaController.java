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
import com.olan.model.Venta;
import com.olan.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {
	
	@Autowired
	private IVentaService service;
	
	@GetMapping
	public ResponseEntity<List<Venta>> listar(){
		List<Venta> listaVenta = service.listar();
		return new ResponseEntity<List<Venta>>(listaVenta, HttpStatus.OK);
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Venta> leerPorId(@PathVariable("id") Integer id) {
		Venta objVenta =service.leerporId(id);
		if(objVenta==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		
		return new ResponseEntity<Venta>(objVenta,HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public Resource<Venta> leerPorIdHateoas(@PathVariable("id") Integer id) {
		Venta objVenta =service.leerporId(id);
		if(objVenta==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		
		Resource<Venta> resource = new Resource<Venta>(objVenta);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
		resource.add(linkTo.withRel("Venta-resource"));	
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Venta obj) {
		Venta  Venta= service.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Venta.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();//  <Object>(HttpStatus.CREATED);
	}	
	@PutMapping
	public ResponseEntity<Object> modificar(@RequestBody Venta pac) {
		service.modificar(pac);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>  eliminar(@PathVariable("id") Integer id) {
		Venta objVenta =service.leerporId(id);
		if(objVenta==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}else {
			service.eliminar(id);
		}
			
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}

