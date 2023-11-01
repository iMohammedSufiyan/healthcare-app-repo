package com.appointment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private String id;
    private String name;
    private String qualification;
    private String specialization;
    private String experience;
    private String description;
    private List<AppointmentDto> listOfAppointments;
}
