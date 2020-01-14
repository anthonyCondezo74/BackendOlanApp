package com.olan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olan.model.Venta;

public interface IVentaRepo extends JpaRepository<Venta, Integer> {

}
