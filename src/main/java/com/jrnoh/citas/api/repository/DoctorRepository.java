package com.jrnoh.citas.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jrnoh.citas.api.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
