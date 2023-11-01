package com.patient.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private String id;

    @NotEmpty(message = "First name should not be empty")
    @NotNull(message = "First name should not be null")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @NotNull(message = "Last name should not be null")
    private String lastName;

    @NotEmpty
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(\\+[0-9]{1,4})?([0-9]{10})$", message = "Invalid mobile number")
    private String mobile;

    @NotEmpty
    @NotNull
    private String address;

    @NotEmpty(message = "Age cannot be empty")
    @NotNull(message = "Age cannot be null")
    private String age;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "^(male|female|transgender)$", message = "Invalid gender")
    private String gender; // Add the gender field

    private Set<AppointmentDto> setOfAppointments;

    private Set<AppointmentDto> setOfAppointmentsHistory;
}
