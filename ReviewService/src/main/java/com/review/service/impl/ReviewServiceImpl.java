package com.review.service.impl;

import com.review.entity.Review;
import com.review.exception.ResourceNotFoundException;
import com.review.payload.ClinicDto;
import com.review.payload.DoctorDto;
import com.review.payload.PatientDto;
import com.review.payload.ReviewDto;
import com.review.repository.ReviewRepository;
import com.review.service.ReviewService;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private RestTemplate restTemplate;
    private ModelMapper modelMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, RestTemplate restTemplate, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewDto saveReview(ReviewDto reviewDto) {
        try {
            restTemplate.getForObject("http://DOCTOR-SERVICE/api/doctor/" + reviewDto.getReviewedEntityId(), DoctorDto.class);
        } catch (HttpClientErrorException e) {
            try {
                restTemplate.getForObject("http://CLINIC-SERVICE/api/clinics/" + reviewDto.getReviewedEntityId(), ClinicDto.class);
            } catch (HttpClientErrorException ex) {
                // Handle the exception if neither doctor nor clinic entity is found
                throw new ResourceNotFoundException("Neither a Doctor nor a Clinic entity was found.");
            }
        }

        try {
            restTemplate.getForObject("http://PATIENT-SERVICE/api/patients/" + reviewDto.getPatientId(), PatientDto.class);
        } catch (HttpClientErrorException ex) {
            String responseBody = ex.getResponseBodyAsString();
            JSONObject jsonObject = new JSONObject(responseBody);
            throw new ResourceNotFoundException(jsonObject.getString("message"));
        }

        reviewDto.setId(UUID.randomUUID().toString());
        return mapToReviewDto(reviewRepository.save(mapToReviewEntity(reviewDto)));
    }

    @Override
    public ReviewDto getReviewByReviewId(String reviewId) {
        return mapToReviewDto(reviewRepository.findById(reviewId).orElseThrow(
                () -> new ResourceNotFoundException("Review not found with id " + reviewId)
        ));
    }

    @Override
    public List<ReviewDto> getReviewsByEntityId(String entityId) {
        try {
            restTemplate.getForObject("http://DOCTOR-SERVICE/api/doctor/" + entityId, DoctorDto.class);
        } catch (HttpClientErrorException e) {
            try {
                restTemplate.getForObject("http://CLINIC-SERVICE/api/clinics/" + entityId, ClinicDto.class);
            } catch (HttpClientErrorException ex) {
                // Handle the exception if neither doctor nor clinic entity is found
                throw new ResourceNotFoundException("Neither a Doctor nor a Clinic entity was found.");
            }
        }

        return reviewRepository.findByReviewedEntityId(entityId)
                .stream()
                .map(review -> mapToReviewDto(review))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto updateReview(String reviewId, ReviewDto reviewDto) {
        return null;
    }

    @Override
    public void deleteReviewById(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    private Review mapToReviewEntity(ReviewDto reviewDto) {
        return modelMapper.map(reviewDto, Review.class);
    }

    private ReviewDto mapToReviewDto(Review review) {
        return modelMapper.map(review, ReviewDto.class);
    }

}
