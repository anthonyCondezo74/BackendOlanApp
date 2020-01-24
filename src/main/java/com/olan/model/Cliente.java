package com.olan.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	@ManyToOne
	@JoinColumn(name="id_tipo_cliente", nullable=false, foreignKey=@ForeignKey(name="fk_cliente_tipoCliente" ))
	private TipoCliente tipoCliente;
	@ManyToOne
	@JoinColumn(name="id_tipo_documento", nullable=false, foreignKey=@ForeignKey(name="fk_cliente_tipoDocumento" ))
	private TipoDocumento tipoDocumento;

	@ApiModelProperty(notes = "Numero debe tener minimo 20 caracteres")
	@Column(name="nro_documento", nullable=true, length=20)
	private Integer nroDocumento;
	
	@ApiModelProperty(notes = "Nombres debe tener minimo 999 caracteres")
	@Size(min=1, message="Nombres debe tener minimo 1 caracteres")
	@Column(name="nombre", nullable=false, length=999)
	private String nombre;
	
	@ApiModelProperty(notes = "Apellido debe tener minimo 999 caracteres")
	@Size(min=1, message="Apellido debe tener minimo 1 caracteres")
	@Column(name="apellido", nullable=false, length=999)
	private String apellido;
	
	@ApiModelProperty(notes = "Estado debe tener minimo 1 caracteres")
	@Size(min=1, message="Estado debe tener minimo 1 caracteres")
	@Column(name="estado", nullable=false, length=1)
	private String estado;
	
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime fecha;
	
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public Integer getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(Integer nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	

	
}
