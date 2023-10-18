package com.dhanesh.DocApp.repository;

import com.dhanesh.DocApp.model.BloodGroup;
import com.dhanesh.DocApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPatientRepo extends JpaRepository<Patient,Integer> {


    Patient findFirstByPatientEmail(String newEmail);

    List<Patient> findByPatientBloodGroup(BloodGroup bloodGroup);
}
