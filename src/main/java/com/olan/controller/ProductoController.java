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
import com.olan.model.Producto;
import com.olan.service.IProductoService;






@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private IProductoService service;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listar(){
		List<Producto> listaProducto = service.listar();
		return new ResponseEntity<List<Producto>>(listaProducto, HttpStatus.OK);
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> leerPorId(@PathVariable("id") Integer id) {
		Producto objProducto =service.leerporId(id);
		if(objProducto==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		
		return new ResponseEntity<Producto>(objProducto,HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public Resource<Producto> leerPorIdHateoas(@PathVariable("id") Integer id) {
		Producto objProducto =service.leerporId(id);
		if(objProducto==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		
		Resource<Producto> resource = new Resource<Producto>(objProducto);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
		resource.add(linkTo.withRel("Producto-resource"));	
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Producto obj) {
		Producto  Producto= service.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Producto.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();//  <Object>(HttpStatus.CREATED);
	}	
	@PutMapping
	public ResponseEntity<Object> modificar(@RequestBody Producto pac) {
		service.modificar(pac);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>  eliminar(@PathVariable("id") Integer id) {
		Producto objProducto =service.leerporId(id);
		if(objProducto==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}else {
			service.eliminar(id);
		}
			
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}

