package com.dhanesh.DocApp.model.dto;

import com.dhanesh.DocApp.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAppointmentDTO {

    AuthenticationInputDto authInfo;
    Appointment appointment;
}