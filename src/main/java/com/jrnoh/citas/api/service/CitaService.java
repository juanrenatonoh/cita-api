package com.jrnoh.citas.api.service;

import java.time.LocalDateTime;
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
		
		// Consultar si ya hay una cita para el mismo consultorio y hora
        List<Cita> citasEnHoraYConsultorio = repository.findByConsultorioNumeroConsultorioAndHorarioConsulta(cita.getConsultorio().getNumeroConsultorio(), cita.getHorarioConsulta());
        if (!citasEnHoraYConsultorio.isEmpty()) {
            throw new RuntimeException("Ya existe una cita para el mismo consultorio y hora ");
        }

       

        // Consultar si el paciente tiene otra cita en el mismo día con menos de 2 horas de diferencia no tenemos un atributo paciente
//        LocalDateTime horaInicio = cita.getHorarioConsulta().minusHours(2);
//        LocalDateTime horaFin = cita.getHorarioConsulta().plusHours(2);
//        List<Cita> citasDelPacienteEnDia = repository.findByPacienteAndHorarioConsultaBetween(cita.getNombrePaciente(), horaInicio, horaFin);
//        if (!citasDelPacienteEnDia.isEmpty()) {
//        	throw new RuntimeException("El paciente tiene una cita pronto");
//        }

        // Consultar si el doctor ya tiene 8 citas en el día
        LocalDateTime inicioDia = cita.getHorarioConsulta().toLocalDate().atStartOfDay();
        LocalDateTime finDia = cita.getHorarioConsulta().toLocalDate().atTime(23, 59);
        long citasDelDoctorEnDia = repository.countByDoctorAndHorarioConsultaBetween(cita.getDoctor().getNombre(), inicioDia, finDia);
        if (citasDelDoctorEnDia >= 8) {
        	throw new RuntimeException("El doctor ya tiene 8 citas");
        }

		this.repository.save(cita);
	}
	
}
