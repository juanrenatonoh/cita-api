package com.jrnoh.citas.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jrnoh.citas.api.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer>,JpaSpecificationExecutor<Cita> {

}
