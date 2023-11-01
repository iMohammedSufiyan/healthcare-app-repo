package com.clinic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private String id;

    @NotEmpty(message = "Reviewed entity's id cannot be empty (Reviewed entity can either be Doctor or Patient)")
    @NotNull(message = "Reviewed entity's id cannot be null (Reviewed entity can either be Doctor or Patient)")
    private String reviewedEntityId; // Reviewed entity (Can be either a Doctor or a Clinic)

    @NotEmpty(message = "Patient id cannot be empty")
    @NotNull(message = "Patient id cannot be null")
    private String patientId;

    @NotEmpty
    @NotNull
    @Min(value = 1, message = "Rating should not be less than 1")
    @Max(value = 5, message = "Rating should not be greater than 5")
    private int rating;

    @NotEmpty(message = "Description cannot be empty")
    @NotNull(message = "Description cannot be null")
    private String comment;

    private LocalDate date;
}
