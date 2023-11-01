package com.review.controller;

import com.review.payload.ReviewDto;
import com.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewDto> saveReview(@RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.saveReview(reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable String reviewId) {
        return new ResponseEntity<>(reviewService.getReviewByReviewId(reviewId), HttpStatus.OK);
    }

    @GetMapping("/entity/{entityId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByEntityId(@PathVariable String entityId) {
        return new ResponseEntity<>(reviewService.getReviewsByEntityId(entityId), HttpStatus.OK);
    }

    @PostMapping("/update/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable String reviewId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.updateReview(reviewId, reviewDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable String reviewId) {
        reviewService.deleteReviewById(reviewId);
        return new ResponseEntity<>("Review deleted successfully.", HttpStatus.OK);
    }
}
