package com.olan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olan.model.TipoDocumento;

public interface ITipoDocumentoRepo extends JpaRepository<TipoDocumento, Integer> {

}
