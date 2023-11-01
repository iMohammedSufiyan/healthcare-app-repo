package com.review.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private String id;

    @NotEmpty(message = "First name cannot be empty")
    @NotNull(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    @NotNull(message = "Last name is required")
    private String lastName;

    @NotEmpty(message = "Email name cannot be empty")
    @NotNull(message = "Email name is required")
    private String email;

    @NotEmpty(message = "Mobile number cannot be empty")
    @NotNull(message = "Mobile number is required")
    private String mobile;

    @NotEmpty(message = "Qualification cannot be empty")
    @NotNull(message = "Qualification is required")
    private String qualification;

    @NotEmpty(message = "Specialization cannot be empty")
    @NotNull(message = "Specialization is required")
    private String specialization;

    @NotEmpty(message = "Experience cannot be empty")
    @NotNull(message = "Experience is required")
    private String experience;

    @NotEmpty(message = "Clinic Id cannot be empty")
    @NotNull(message = "Clinic Id is required")
    private String clinicId;

    @NotEmpty(message = "Consultation fee cannot be empty")
    @NotNull(message = "Consultation fee is required")
    private long consultationFee;

    @NotEmpty(message = "Description cannot be empty")
    @NotNull(message = "Description is required")
    private String description;

    private Set<AppointmentDto> setOfAppointments;
    private List<ReviewDto> listOfReviews;
}
