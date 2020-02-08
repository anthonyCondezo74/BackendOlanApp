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
import com.olan.validator.RazonSocial;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "cliente")
@RazonSocial
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_tipo_cliente", nullable = false, foreignKey = @ForeignKey(name = "fk_cliente_tipoCliente"))
	private TipoCliente tipoCliente;

	@ManyToOne
	@JoinColumn(name = "id_tipo_documento", nullable = false, foreignKey = @ForeignKey(name = "fk_cliente_tipoDocumento"))
	private TipoDocumento tipoDocumento;

	@Column(nullable = false, length = 20)
	private String nroDocumento;

	@Column(length = 60)
	private String nombre;

	@Column(length = 30)
	private String apellidoPaterno;

	private String razonSocial;

	@Column(length = 30)
	private String apellidoMaterno;

	@ApiModelProperty(notes = "Estado debe tener minimo 1 caracteres")
	@Size(min = 1, message = "Estado debe tener minimo 1 caracteres")
	@Column(name = "estado", nullable = false, length = 1)
	private String estado = "A";

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(nullable = false)
	private LocalDateTime fechaRegistro = LocalDateTime.now();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

}
