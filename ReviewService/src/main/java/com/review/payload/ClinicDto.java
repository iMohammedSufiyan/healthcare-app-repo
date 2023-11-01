package com.review.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicDto {
    private String id;

    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name is required")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    @NotNull(message = "Description is required")
    private String description;

    @NotEmpty(message = "Address cannot be empty")
    @NotNull(message = "Address is required")
    private String address;

    @NotEmpty(message = "Contact number cannot be empty")
    @NotNull(message = "Contact number is required")
    @Pattern(regexp = "^\\+[0-9]+[0-9]*$", message = "Invalid phone number format")
    private String contactNumber;

    @NotEmpty(message = "Email cannot be empty")
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private List<DoctorDto> listOfDoctors;
    private List<MedicalServiceDto> listOfServicesOffered;
    private List<ReviewDto> listOfReviews;
    private Set<String> setOfTimeSlots;
}
