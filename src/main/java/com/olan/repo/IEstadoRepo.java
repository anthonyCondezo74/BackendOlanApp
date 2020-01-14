package com.olan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olan.model.Estado;

public interface IEstadoRepo extends JpaRepository<Estado, Integer> {

}
