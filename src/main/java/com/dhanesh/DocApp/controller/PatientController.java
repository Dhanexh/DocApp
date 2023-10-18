package com.dhanesh.DocApp.controller;

import com.dhanesh.DocApp.model.Doctor;
import com.dhanesh.DocApp.model.Patient;
import com.dhanesh.DocApp.model.Qualification;
import com.dhanesh.DocApp.model.Specialization;
import com.dhanesh.DocApp.model.dto.AuthenticationInputDto;
import com.dhanesh.DocApp.model.dto.ScheduleAppointmentDTO;
import com.dhanesh.DocApp.model.dto.SignInInputDto;
import com.dhanesh.DocApp.service.AppointmentService;
import com.dhanesh.DocApp.service.DoctorService;
import com.dhanesh.DocApp.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DoctorService doctorService;


    //sign up
    @PostMapping("patient/signup")
    public String patientSignUp(@Valid @RequestBody Patient patient)
    {
        return patientService.patientSignUp(patient);
    }



    //sign in
    @PostMapping("patient/signIn")
    public String patientSignIn(@RequestBody SignInInputDto signInInput)
    {
        return patientService.patientSignIn(signInInput);
    }


    //sign out
    @DeleteMapping("patient/signOut")
    public String patientSignOut(@RequestBody AuthenticationInputDto authInfo)
    {
        return patientService.patientSignOut(authInfo);
    }


    //schedule an appointment

    @PostMapping("patient/appointment/schedule")
    public String scheduleAppointment(@RequestBody ScheduleAppointmentDTO scheduleAppointmentDTO)
    {
        return appointmentService.scheduleAppointment(scheduleAppointmentDTO.getAuthInfo(),scheduleAppointmentDTO.getAppointment());
    }

    @DeleteMapping("patient/appointment/{appointmentId}/cancel")
    public String cancelAppointment(@RequestBody AuthenticationInputDto authInfo, @PathVariable Integer appointmentId)
    {
        return appointmentService.cancelAppointment(authInfo,appointmentId);
    }

    @GetMapping("doctors/qualification/{qual}/or/specialization/{spec}")
    public List<Doctor> getDoctorsByQualificationOrSpec(@RequestBody AuthenticationInputDto authInfo, @PathVariable Qualification qual, @PathVariable Specialization spec)
    {
        return doctorService.getDoctorsByQualificationOrSpec(authInfo,qual,spec);
    }




}

