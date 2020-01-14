package com.olan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olan.model.Cliente;

public interface IClienteRepo extends JpaRepository<Cliente, Integer> {

}
