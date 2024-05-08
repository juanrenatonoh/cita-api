package com.jrnoh.citas.api.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrnoh.citas.api.dto.FiltroConsultaCita;
import com.jrnoh.citas.api.entity.Doctor;
import com.jrnoh.citas.api.repository.DoctorRepository;

@RestController
@RequestMapping("doctor")
public class DoctorRest {
	
	protected DoctorRepository repository;
	
	public DoctorRest(DoctorRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Doctor>> consulta(FiltroConsultaCita filtro){
		return ResponseEntity.ok(repository.findAll());
	}

}
