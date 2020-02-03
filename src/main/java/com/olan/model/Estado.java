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

@ApiModel(description = "Informacion del estado del campo")
@Entity
@Table(name = "estado")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty(notes = "Nombres debe tener minimo 3 caracteres")
	@Size(min = 1, message = "Nombres debe tener minimo 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 70)
	private String nombre;

	@ApiModelProperty(notes = "Estado debe tener minimo 1 caracteres")
	@Size(min = 1, message = "Estado debe tener minimo 1 caracteres")
	@Column(name = "estado", nullable = false, length = 1)
	private String estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		if (estado.equals("I")) {
			this.estado = "INACTIVO";
		}
		if (estado.equals("A")) {
			this.estado = "ACTIVO";
		}
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
