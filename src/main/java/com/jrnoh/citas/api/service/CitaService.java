package com.jrnoh.citas.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jrnoh.citas.api.dto.FiltroConsultaCita;
import com.jrnoh.citas.api.entity.Cita;
import com.jrnoh.citas.api.entity.Consultorio;
import com.jrnoh.citas.api.repository.CitaRepository;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

@Service
public class CitaService {

	protected CitaRepository repository;
	
	public CitaService(CitaRepository repository) {
		this.repository = repository;
	}
	
	public List<Cita> consultar(FiltroConsultaCita filtro){
		
		Specification<Cita> specificationGet =  Specification.where((root, query, cb) -> {
			
			List<Predicate> predicates = new ArrayList<>();
			
			if(filtro.getFecha() != null) {
				predicates.add(cb.equal(root.get("fecha"), filtro.getFecha()));
			}
			
			try {
				var numeroConsultorio =   Integer.parseInt(filtro.getNumeroConsultorio());
				
				if(numeroConsultorio > 0) {
					
					Join<Cita, Consultorio> consultorio = root.join("consultorio");
					predicates.add(cb.equal(consultorio.get("numeroConsultorio"), filtro.getNumeroConsultorio()));
				}
			}catch (Exception e) {
			}
			
			
			return cb.and(predicates.toArray(new Predicate[0]));
			
		});
		
		return repository.findAll(specificationGet);
	}
	
	public void nuevaCita(Cita cita) {
		
		
		
		this.repository.save(cita);
	}
	
}
