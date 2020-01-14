package com.olan.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="reserva")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idReserva;
	
	@ManyToOne
	@JoinColumn(name="id_cliente", nullable=false, foreignKey=@ForeignKey(name="fk_reserva_cliente" ))
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="id_campoDeportivo", nullable=false, foreignKey=@ForeignKey(name="fk_reserva_campoDeportivo" ))
	private CampoDeportivo campoDeportivo;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime fecha;
	
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime hora;
	
	@ApiModelProperty(notes = "Tiempo debe tener minimo 1 caracteres")
	@Column(name="tiempo", nullable=false)
	private Integer tiempo;
	
	@ApiModelProperty(notes = "Precio")
	@Column(name="precio", nullable=false, length=100)
	private double precio;
	
	@ManyToOne
	@JoinColumn(name="id_estado", nullable=false, foreignKey=@ForeignKey(name="fk_reserva_estado" ))
	private Estado estado;

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public LocalDateTime getHora() {
		return hora;
	}

	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public CampoDeportivo getCampoDeportivo() {
		return campoDeportivo;
	}

	public void setCampoDeportivo(CampoDeportivo campoDeportivo) {
		this.campoDeportivo = campoDeportivo;
	}

	

	
}
