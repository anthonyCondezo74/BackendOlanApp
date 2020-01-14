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

@ApiModel(description= "Informacion del campo de producto")
@Entity
@Table(name="producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	
	@ApiModelProperty(notes = "Nombres debe tener minimo 3 caracteres")
	@Size(min=3, message="Nombres debe tener minimo 3 caracteres")
	@Column(name="nombre", nullable=false, length=70)
	private String nombre;
	
	@ApiModelProperty(notes = "Codigo debe tener minimo 1 caracteres")
	@Size(min=1, message="Nombres debe tener minimo 1 caracteres")
	@Column(name="codigo", nullable=false, length=100)
	private String codigo;
	
	
	@ApiModelProperty(notes = "Precio")
	@Column(name="precio", nullable=false, length=100)
	private double precio;


	public Integer getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	
}
