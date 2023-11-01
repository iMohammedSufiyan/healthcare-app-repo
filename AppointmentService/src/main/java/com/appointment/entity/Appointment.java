package com.appointment.entity;


import com.appointment.enums.TimeSlot;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    private String id;

    @Column(nullable = false)
    private String patientId;

    @Column(nullable = false)
    private String doctorId;

    @Column(nullable = false)
    private LocalDate appointmentBookingDate;

    @Column(nullable = false)
    private LocalTime appointmentBookingTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Use EnumType.STRING to store enum values as strings
    private TimeSlot selectedTimeSlot;
}
