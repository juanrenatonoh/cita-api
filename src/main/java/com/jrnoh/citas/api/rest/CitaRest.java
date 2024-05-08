package com.jrnoh.citas.api.rest;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrnoh.citas.api.dto.FiltroConsultaCita;
import com.jrnoh.citas.api.entity.Cita;
import com.jrnoh.citas.api.service.CitaService;

@RestController
@RequestMapping("cita")
public class CitaRest {
	
	protected CitaService service;
	
	public CitaRest(CitaService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Cita>> consulta(FiltroConsultaCita filtro){
		return ResponseEntity.ok(service.consultar(filtro));
	}
	
	@PostMapping("/")
	public void crear(Cita cita){
		service.nuevaCita(cita);
	}

}
