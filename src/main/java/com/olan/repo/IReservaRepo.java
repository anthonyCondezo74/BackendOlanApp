package com.olan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olan.model.Reserva;

public interface IReservaRepo extends JpaRepository<Reserva, Integer> {

}
