package com.jrnoh.citas.api.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrnoh.citas.api.entity.Consultorio;
import com.jrnoh.citas.api.repository.ConsultorioRepository;

@RestController
@RequestMapping("consultorio")
public class ConsultorioRest {
	
	protected ConsultorioRepository repository;
	
	public ConsultorioRest(ConsultorioRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Consultorio>> consulta(){
		return ResponseEntity.ok(repository.findAll());
	}

}
