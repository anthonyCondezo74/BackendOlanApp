package com.olan.service;

import java.util.List;

import com.olan.dto.ConsultaResumenDTO;
import com.olan.dto.FiltroReservaDTO;
import com.olan.model.Reserva;

public interface IReservaService extends ICRUD<Reserva> {

	List<Reserva> buscar(FiltroReservaDTO filtro);
	List<Reserva> buscarFecha(FiltroReservaDTO filtro);
	List<ConsultaResumenDTO> listarResumen();
}
