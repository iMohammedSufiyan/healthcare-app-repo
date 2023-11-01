package com.appointment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private String id;

    @NotEmpty
    private String patientId;

    @NotEmpty
    private String doctorId;

    private LocalDate appointmentBookingDate;

    private LocalTime appointmentBookingTime;

    @NotNull(message = "Time Slot cannot be null")
    @NotEmpty(message = "Time Slot cannot be empty")
    private String selectedTimeSlot;
}
