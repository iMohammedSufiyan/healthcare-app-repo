package com.doctor.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String disease;

    @NotNull
    private int age;
}
