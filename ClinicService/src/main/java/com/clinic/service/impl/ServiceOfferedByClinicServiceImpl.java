package com.clinic.service.impl;

import com.clinic.entity.ServiceOfferedByClinic;
import com.clinic.payload.ServiceOfferedByClinicDto;
import com.clinic.repository.ServiceOfferedByClinicRepository;
import com.clinic.service.ServiceOfferedByClinicService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServiceOfferedByClinicServiceImpl implements ServiceOfferedByClinicService {
    private ServiceOfferedByClinicRepository serviceOfferedByClinicRepository;

    private RestTemplate restTemplate;

    private ModelMapper modelMapper;

    public ServiceOfferedByClinicServiceImpl(ServiceOfferedByClinicRepository serviceOfferedByClinicRepository, RestTemplate restTemplate, ModelMapper modelMapper) {
        this.serviceOfferedByClinicRepository = serviceOfferedByClinicRepository;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }

    @Override
    public ServiceOfferedByClinicDto saveServiceOfferedByClinic(ServiceOfferedByClinicDto serviceOfferedByClinicDto) {
        serviceOfferedByClinicDto.setId(UUID.randomUUID().toString());
        return mapToServiceOfferedByClinicDto(serviceOfferedByClinicRepository.save(mapToServiceOfferedByClinicEntity(serviceOfferedByClinicDto)));
    }

    @Override
    public ServiceOfferedByClinicDto getServiceOfferedByClinicById(String serviceOfferedId) {
        return mapToServiceOfferedByClinicDto(serviceOfferedByClinicRepository.findById(serviceOfferedId).orElseThrow(
                () -> new ResourceAccessException("Service offered by clinic not found with id: " + serviceOfferedId)
        ));
    }

    @Override
    public List<ServiceOfferedByClinicDto> getServiceOfferedByClinicByMedicalServiceId(String medicalServiceId) {
        return serviceOfferedByClinicRepository.findByMedicalServiceId(medicalServiceId).stream()
                .map(serviceOfferedByClinic -> mapToServiceOfferedByClinicDto(serviceOfferedByClinic))
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceOfferedByClinicDto> getServiceOfferedByClinicByClinicId(String clinicId) {
        return serviceOfferedByClinicRepository.findByClinicId(clinicId).stream()
                .map(serviceOfferedByClinic -> mapToServiceOfferedByClinicDto(serviceOfferedByClinic))
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceOfferedByClinicDto> getServiceOfferedByClinicByDoctorId(String doctorId) {
        return serviceOfferedByClinicRepository.findByDoctorId(doctorId).stream()
                .map(serviceOfferedByClinic -> mapToServiceOfferedByClinicDto(serviceOfferedByClinic))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceOfferedByClinicDto updateServiceOfferedByClinicById(ServiceOfferedByClinicDto serviceOfferedByClinicDto) {
        return null;
    }

    @Override
    public void deleteServiceOfferedByClinicById(String serviceOfferedByClinicId) {
        serviceOfferedByClinicRepository.deleteById(serviceOfferedByClinicId);
    }

    private ServiceOfferedByClinic mapToServiceOfferedByClinicEntity(ServiceOfferedByClinicDto serviceOfferedByClinicDto) {
        return modelMapper.map(serviceOfferedByClinicDto, ServiceOfferedByClinic.class);
    }

    private ServiceOfferedByClinicDto mapToServiceOfferedByClinicDto(ServiceOfferedByClinic serviceOfferedByClinic) {
        return modelMapper.map(serviceOfferedByClinic, ServiceOfferedByClinicDto.class);
    }
}
