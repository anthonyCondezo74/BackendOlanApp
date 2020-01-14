package com.olan.service;

import com.olan.model.Venta;

public interface IVentaService extends ICRUD<Venta> {

	Venta registrar(Venta obj);
}
