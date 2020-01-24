package com.olan.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
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

import com.olan.dto.ConsultaResumenDTO;
import com.olan.dto.FiltroReservaDTO;
import com.olan.exception.ModelNotFoundException;
import com.olan.model.Reserva;
import com.olan.service.IReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
	
	@Autowired
	private IReservaService service;
	
	@GetMapping
	public ResponseEntity<List<Reserva>> listar(){
		List<Reserva> listaReserva = service.listar();
		return new ResponseEntity<List<Reserva>>(listaReserva, HttpStatus.OK);
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Reserva> leerPorId(@PathVariable("id") Integer id) {
		Reserva objReserva =service.leerporId(id);
		if(objReserva==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		
		return new ResponseEntity<Reserva>(objReserva,HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public Resource<Reserva> leerPorIdHateoas(@PathVariable("id") Integer id) {
		Reserva objReserva =service.leerporId(id);
		if(objReserva==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		
		Resource<Reserva> resource = new Resource<Reserva>(objReserva);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
		resource.add(linkTo.withRel("Reserva-resource"));	
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Reserva obj) {
		Reserva  Reserva= service.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Reserva.getIdReserva()).toUri();
		return ResponseEntity.created(location).build();//  <Object>(HttpStatus.CREATED);
	}	
	@PutMapping
	public ResponseEntity<Object> modificar(@RequestBody Reserva pac) {
		service.modificar(pac);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>  eliminar(@PathVariable("id") Integer id) {
		Reserva objReserva =service.leerporId(id);
		if(objReserva==null) {
			throw  new ModelNotFoundException("ID NO ENCONTRADO: "+ id);
		}else {
			service.eliminar(id);
		}
			
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@PostMapping("/buscar")
	public ResponseEntity<List<Reserva>> buscar(@RequestBody FiltroReservaDTO filtro) {
		List<Reserva> consultas = new ArrayList<>();

		if (filtro != null) {
			if (filtro.getFechaReserva() !=  null) {
				consultas = service.buscarFecha(filtro);
			} else {
				consultas = service.buscar(filtro);
			}
		}
		return new ResponseEntity<List<Reserva>>(consultas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarResumen")
	public ResponseEntity<List<ConsultaResumenDTO>> listarResumen() {
		List<ConsultaResumenDTO> consultas = new ArrayList<>();
		consultas = service.listarResumen();
		return new ResponseEntity<List<ConsultaResumenDTO>>(consultas, HttpStatus.OK);
	}
}

