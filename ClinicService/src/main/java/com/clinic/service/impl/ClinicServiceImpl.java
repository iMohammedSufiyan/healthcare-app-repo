package com.clinic.service.impl;

import com.clinic.entity.Clinic;
import com.clinic.payload.*;
import com.clinic.repository.ClinicRepository;
import com.clinic.service.ClinicService;
import com.clinic.service.MedicalServiceService;
import com.clinic.service.ServiceOfferedByClinicService;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClinicServiceImpl implements ClinicService {
    private ClinicRepository clinicRepository;
    private ServiceOfferedByClinicService serviceOfferedByClinicService;
    private MedicalServiceService medicalServiceService;
    private RestTemplate restTemplate;
    private ModelMapper modelMapper;

    public ClinicServiceImpl(ClinicRepository clinicRepository, ServiceOfferedByClinicService serviceOfferedByClinicService, MedicalServiceService medicalServiceService, RestTemplate restTemplate, ModelMapper modelMapper) {
        this.clinicRepository = clinicRepository;
        this.serviceOfferedByClinicService = serviceOfferedByClinicService;
        this.medicalServiceService = medicalServiceService;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClinicDto saveClinic(ClinicDto clinicDto) {
        clinicDto.setId(UUID.randomUUID().toString());
        return mapToClinicDto(clinicRepository.save(mapToClinicEntity(clinicDto)));
    }

    @Override
    public ClinicDto getClinicById(String clinicId) {
        ClinicDto clinicDto = mapToClinicDto(clinicRepository.findById(clinicId).orElseThrow(
                () -> new ResourceAccessException("Clinic not found with id: " + clinicId)
        ));

        List<DoctorDto> listOfDoctors = null;
        List<ServiceOfferedByClinicDto> listOfServicesOfferedByClinic = null;
        List<MedicalServiceDto> listOfServicesOffered = null;
        List<ReviewDto> listOfReviews = null;

        try {
            listOfDoctors = restTemplate.exchange("http://DOCTOR-SERVICE/api/doctor/clinic/" + clinicId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<DoctorDto>>() {
                    }).getBody();

            listOfServicesOfferedByClinic = serviceOfferedByClinicService.getServiceOfferedByClinicByClinicId(clinicId);

            listOfServicesOffered = listOfServicesOfferedByClinic.stream()
                    .map(serviceOfferedByClinic -> medicalServiceService.getMedicalServiceById(serviceOfferedByClinic.getMedicalServiceId()))
                    .collect(Collectors.toList());

            listOfReviews = restTemplate.exchange("http://REVIEW-SERVICE/api/reviews/entity/" + clinicId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ReviewDto>>() {
                    }).getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }

        clinicDto.setListOfDoctors(listOfDoctors);
        clinicDto.setListOfServicesOffered(listOfServicesOffered);
        clinicDto.setListOfReviews(listOfReviews);

        return clinicDto;
    }

    @Override
    public ClinicDto getClinicByName(String clinicName) {
        ClinicDto clinicDto = mapToClinicDto(clinicRepository.findByName(clinicName).orElseThrow(
                () -> new ResourceAccessException("Could not find clinic with name: " + clinicName)
        ));

        List<DoctorDto> listOfDoctors = null;
        List<ServiceOfferedByClinicDto> listOfServicesOfferedByClinic = null;
        List<MedicalServiceDto> listOfServicesOffered = null;
        List<ReviewDto> listOfReviews = null;

        try {
            listOfDoctors = restTemplate.exchange("http://DOCTOR-SERVICE/api/doctor/clinic/" + clinicDto.getId(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<DoctorDto>>() {
                    }).getBody();

            listOfServicesOfferedByClinic = serviceOfferedByClinicService.getServiceOfferedByClinicByClinicId(clinicDto.getId());

            listOfServicesOffered = listOfServicesOfferedByClinic.stream()
                    .map(serviceOfferedByClinic -> medicalServiceService.getMedicalServiceById(serviceOfferedByClinic.getMedicalServiceId()))
                    .collect(Collectors.toList());

            listOfReviews = restTemplate.exchange("http://REVIEW-SERVICE/api/reviews/entity/" + clinicDto.getId(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ReviewDto>>() {
                    }).getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }

        clinicDto.setListOfDoctors(listOfDoctors);
        clinicDto.setListOfServicesOffered(listOfServicesOffered);
        clinicDto.setListOfReviews(listOfReviews);

        return clinicDto;
    }

    @Override
    public ClinicDto updateClinicById(String clinicId, ClinicDto clinicDto) {
        return null;
    }

    @Override
    public void deleteClinicById(String clinicId) {
         /*

         clinic is whole, doctor and reviews are part,
         so before deleting clinic,
         make sure that you first delete reviews of clinic and doctors that works in the clinic,
         after that delete clinic.

        */
    }

    private Clinic mapToClinicEntity(ClinicDto clinicDto) {
        return modelMapper.map(clinicDto, Clinic.class);
    }

    private ClinicDto mapToClinicDto(Clinic clinic) {
        return modelMapper.map(clinic, ClinicDto.class);
    }
}
