package com.dhanesh.DocApp.service;

import com.dhanesh.DocApp.model.Appointment;
import com.dhanesh.DocApp.model.Doctor;
import com.dhanesh.DocApp.model.Patient;
import com.dhanesh.DocApp.model.dto.AuthenticationInputDto;
import com.dhanesh.DocApp.repository.IAppointmentRepo;
import com.dhanesh.DocApp.repository.IDoctorRepo;
import com.dhanesh.DocApp.repository.IPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepo appointmentRepo;


    @Autowired
    IPatientRepo patientRepo;


    @Autowired
    IDoctorRepo doctorRepo;



    @Autowired
    PTokenService pTokenService;

    public String scheduleAppointment(AuthenticationInputDto authInfo, Appointment appointment) {

        if(pTokenService.authenticate(authInfo)) {

            //find thr patient
            String email = authInfo.getEmail();

            Patient patient = patientRepo.findFirstByPatientEmail(email);

            appointment.setPatient(patient);


            //find the doctor

            Integer docId = appointment.getDoctor().getDocId();

            Doctor doc = doctorRepo.findById(docId).orElseThrow();

            appointment.setDoctor(doc);


            if(doc!= null)
            {
                appointment.setAppCreationTime(LocalDateTime.now());
                appointmentRepo.save(appointment);
                return "appointment booked at time :" + appointment.getAppScheduleTime() + " with Dr. " + doc.getDocName() ;
            }
            else
            {
                return "Doctor does not exist, Could not book appointment!!!";
            }
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String cancelAppointment(AuthenticationInputDto authInfo, Integer appointmentId) {

        if(pTokenService.authenticate(authInfo)) {

            String email = authInfo.getEmail();

            Patient patient = patientRepo.findFirstByPatientEmail(email);

            Appointment existingAppointment =  appointmentRepo.findById(appointmentId).orElseThrow();

            if(existingAppointment.getPatient().equals(patient))
            {
                appointmentRepo.deleteById(appointmentId);
                return "appointment with " + existingAppointment.getDoctor().getDocName() + " has been cancelled!!";

            }
            else
            {
                return "UnAuthorized cancel appointment!!";
            }

        }
        else {
            return "Un Authenticated access!!!";
        }
    }
}