package com.olan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olan.model.TipoCliente;

public interface ITipoClienteRepo extends JpaRepository<TipoCliente, Integer> {

}
