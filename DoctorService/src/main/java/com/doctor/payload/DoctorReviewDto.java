package com.doctor.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorReviewDto {
    private DoctorDto doctorDto;
    private double ratingPercentage;
    private List<ReviewDto> listOfReviews;
}
