package com.jrnoh.citas.api.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.jrnoh.citas.api.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer>,JpaSpecificationExecutor<Cita> {
	
	List<Cita> findByConsultorioNumeroConsultorioAndHorarioConsulta(Integer consultorio, LocalDateTime horarioConsulta);

    

    List<Cita> findByPacienteAndHorarioConsultaBetween(String paciente, LocalDateTime inicio, LocalDateTime fin);

    @Query("SELECT COUNT(c) FROM Cita c WHERE c.doctor = ?1 AND c.horarioConsulta BETWEEN ?2 AND ?3")
    long countByDoctorAndHorarioConsultaBetween(String doctor, LocalDateTime inicio, LocalDateTime fin);
	
}
