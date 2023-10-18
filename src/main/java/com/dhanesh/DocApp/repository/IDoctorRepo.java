package com.dhanesh.DocApp.repository;

import com.dhanesh.DocApp.model.Doctor;
import com.dhanesh.DocApp.model.Qualification;
import com.dhanesh.DocApp.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDoctorRepo extends JpaRepository<Doctor,Integer> {
    List<Doctor> findByDocQualificationOrDocSpecialization(Qualification qual, Specialization spec);
}
