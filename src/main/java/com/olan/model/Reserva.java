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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false, foreignKey = @ForeignKey(name = "fk_reserva_cliente"))
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_campo_deportivo", nullable = false, foreignKey = @ForeignKey(name = "fk_reserva_campoDeportivo"))
	private CampoDeportivo campoDeportivo;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(nullable = false)
	private LocalDateTime fechaHoraInicio;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(nullable = false)
	private LocalDateTime fechaHoraFinal;

	@ApiModelProperty(notes = "Precio")
	@Column(name = "precio", nullable = false, length = 100)
	private double precio;

	@ManyToOne
	@JoinColumn(name = "id_tiempo_alquiler", nullable = false, foreignKey = @ForeignKey(name = "fk_reserva_tiempo_alquiler"))
	private TiempoAlquiler tiempoAlquiler;

	@ManyToOne
	@JoinColumn(name = "id_estado", nullable = false, foreignKey = @ForeignKey(name = "fk_reserva_estado"))
	private Estado estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public CampoDeportivo getCampoDeportivo() {
		return campoDeportivo;
	}

	public void setCampoDeportivo(CampoDeportivo campoDeportivo) {
		this.campoDeportivo = campoDeportivo;
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

	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public LocalDateTime getFechaHoraFinal() {
		return fechaHoraFinal;
	}

	public void setFechaHoraFinal(LocalDateTime fechaHoraFinal) {
		this.fechaHoraFinal = fechaHoraFinal;
	}

	public TiempoAlquiler getTiempoAlquiler() {
		return tiempoAlquiler;
	}

	public void setTiempoAlquiler(TiempoAlquiler tiempoAlquiler) {
		this.tiempoAlquiler = tiempoAlquiler;
	}

}
