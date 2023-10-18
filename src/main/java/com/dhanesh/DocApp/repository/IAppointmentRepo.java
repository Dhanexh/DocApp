package com.dhanesh.DocApp.repository;

import com.dhanesh.DocApp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepo extends JpaRepository<Appointment,Integer> {
}
