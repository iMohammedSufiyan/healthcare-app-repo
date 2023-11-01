package com.appointment.service;

import com.appointment.payload.AppointmentDto;
import com.appointment.payload.DoctorDto;

public interface AppointmentService {
    AppointmentDto saveAppointment(AppointmentDto appointmentDto);

    DoctorDto getAppointsByDoctorId(String doctorId);
}
