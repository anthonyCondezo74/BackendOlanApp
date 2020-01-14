package com.olan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olan.model.CampoDeportivo;

@Repository
public interface ICampoDeportivoRepo extends JpaRepository<CampoDeportivo, Integer> {

}
