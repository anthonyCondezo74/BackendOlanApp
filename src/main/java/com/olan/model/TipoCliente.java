package com.olan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description= "Informacion del campo de tipo de cliente")
@Entity
@Table(name="tipo_cliente")
public class TipoCliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoCliente;
	
	@ApiModelProperty(notes = "Nombres debe tener minimo 1 caracteres")
	@Size(min=1, message="Nombres debe tener minimo 1 caracteres")
	@Column(name="nombre", nullable=false, length=500)
	private String nombre;
	
	@ApiModelProperty(notes = "Descripcion debe tener minimo 1 caracteres")
	@Size(min=1, message="Descripcion debe tener minimo 1 caracteres")
	@Column(name="descripcion", nullable=false, length=999)
	private String descripcion;
	
	@ApiModelProperty(notes = "Nombres debe tener minimo 1 caracteres")
	@Size(min=1, message="Nombres debe tener minimo 1 caracteres")
	@Column(name="estado", nullable=false, length=1)
	private String estado;

	public Integer getIdTipoCliente() {
		return idTipoCliente;
	}

	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		if (estado.equals("I")) {
			this.estado="INACTIVO";
		}
		if (estado.equals("A")) {
			this.estado="ACTIVO";
		}
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
	
}
