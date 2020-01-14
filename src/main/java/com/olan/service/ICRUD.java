package com.olan.service;

import java.util.List;

public interface ICRUD<T> {

	T registrar(T obj);
	void modificar(T obj);
	List<T>listar();
	T leerporId(Integer id);
	void eliminar(Integer id);
	
}
