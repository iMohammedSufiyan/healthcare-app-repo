package com.review.payload;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDto {
    private String id;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentBookingDate;
    private LocalTime appointmentBookingTime;
    private String selectedTimeSlot;
}