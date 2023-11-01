package com.review.service;

import com.review.payload.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto saveReview(ReviewDto reviewDto);

    ReviewDto getReviewByReviewId(String reviewId);

    List<ReviewDto> getReviewsByEntityId(String entityId); // Reviewed entity (Can be either a Doctor or a Clinic)

    ReviewDto updateReview(String reviewId, ReviewDto reviewDto);

    void deleteReviewById(String reviewId);
}
