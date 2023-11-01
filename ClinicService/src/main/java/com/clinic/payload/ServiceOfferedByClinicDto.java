package com.clinic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOfferedByClinicDto {
    private String id;

    @NotEmpty(message = "Medical service ID is required")
    private String medicalServiceId;

    @NotEmpty(message = "Clinic ID is required")
    private String clinicId;

    @NotEmpty(message = "Doctor ID is required")
    private String doctorId;
}
