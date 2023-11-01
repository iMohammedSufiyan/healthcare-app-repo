package com.doctor.payload;

import java.time.*;

public class AppointmentDto {
    private String id;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentBookingDate;
    private LocalTime appointmentBookingTime;
    private String selectedTimeSlot;
}