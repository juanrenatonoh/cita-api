package com.jrnoh.citas.api.dto;

import java.time.LocalDate;

public class FiltroConsultaCita {
	
	private LocalDate fecha;
	
	private String numeroConsultorio;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getNumeroConsultorio() {
		return numeroConsultorio;
	}

	public void setNumeroConsultorio(String numeroConsultorio) {
		this.numeroConsultorio = numeroConsultorio;
	}
	
	

}
