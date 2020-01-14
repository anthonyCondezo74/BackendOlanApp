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
import com.olan.model.Cliente;
import com.olan.service.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService service;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar(){
		List<Cliente> listaCliente = service.listar();
		return new ResponseEntity<List<Cliente>>(listaCliente, HttpStatus.OK);
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> leerPorId(@PathVariable("id") Integer id) {
		Cliente objCliente =service.leerporId(id);
		if(objCliente==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		
		return new ResponseEntity<Cliente>(objCliente,HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public Resource<Cliente> leerPorIdHateoas(@PathVariable("id") Integer id) {
		Cliente objCliente =service.leerporId(id);
		if(objCliente==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		
		Resource<Cliente> resource = new Resource<Cliente>(objCliente);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
		resource.add(linkTo.withRel("Cliente-resource"));	
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Cliente obj) {
		Cliente  Cliente= service.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Cliente.getIdCliente()).toUri();
		return ResponseEntity.created(location).build();//  <Object>(HttpStatus.CREATED);
	}	
	@PutMapping
	public ResponseEntity<Object> modificar(@RequestBody Cliente pac) {
		service.modificar(pac);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>  eliminar(@PathVariable("id") Integer id) {
		Cliente objCliente =service.leerporId(id);
		if(objCliente==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}else {
			service.eliminar(id);
		}
			
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}

