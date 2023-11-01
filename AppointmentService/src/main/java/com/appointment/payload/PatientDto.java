package com.appointment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String mobile;

    @NotEmpty
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotEmpty
    private String disease;

    @NotNull
    private int age;
}
