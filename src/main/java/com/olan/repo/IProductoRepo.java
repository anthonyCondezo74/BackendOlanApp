package com.olan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olan.model.CampoDeportivo;
import com.olan.model.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Integer> {

}
