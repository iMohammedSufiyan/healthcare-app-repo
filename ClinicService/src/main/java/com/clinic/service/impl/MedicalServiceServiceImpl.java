package com.clinic.service.impl;

import com.clinic.entity.MedicalService;
import com.clinic.exception.EntityAlreadyExistsException;
import com.clinic.payload.MedicalServiceDto;
import com.clinic.repository.MedicalServiceRepository;
import com.clinic.service.MedicalServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicalServiceServiceImpl implements MedicalServiceService {
    private MedicalServiceRepository medicalServiceRepository;

    private RestTemplate restTemplate;

    private ModelMapper modelMapper;

    public MedicalServiceServiceImpl(MedicalServiceRepository medicalServiceRepository, RestTemplate restTemplate, ModelMapper modelMapper) {
        this.medicalServiceRepository = medicalServiceRepository;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }

    @Override
    public MedicalServiceDto saveMedicalService(MedicalServiceDto medicalServiceDto) {
        if (medicalServiceRepository.findByName(medicalServiceDto.getName()).isPresent())
            throw new EntityAlreadyExistsException("A medical service with the same name already exists.");

        medicalServiceDto.setId(UUID.randomUUID().toString());
        return mapToMedicationServiceDto(medicalServiceRepository.save(mapToMedicationServiceEntity(medicalServiceDto)));
    }

    @Override
    public MedicalServiceDto getMedicalServiceById(String medicalServiceId) {
        return mapToMedicationServiceDto(medicalServiceRepository.findById(medicalServiceId).orElseThrow(
                () -> new ResourceAccessException("No such service found for id " + medicalServiceId)
        ));
    }

    @Override
    public MedicalServiceDto getMedicalServiceByName(String medicalServiceName) {
        return mapToMedicationServiceDto(medicalServiceRepository.findByName(medicalServiceName).orElseThrow(
                () -> new ResourceAccessException("No such service found with name " + medicalServiceName)
        ));
    }

    @Override
    public List<MedicalServiceDto> getAllMedicalServices() {
        return medicalServiceRepository.findAll()
                .stream()
                .map(medicalService -> mapToMedicationServiceDto(medicalService))
                .collect(Collectors.toList());
    }

    @Override
    public MedicalServiceDto updateMedicalServiceById(String medicalServiceId, MedicalServiceDto medicalServiceDto) {
        return null;
    }

    @Override
    public void deleteMedicalServiceById(String medicalServiceId) {
        medicalServiceRepository.deleteById(medicalServiceId);
    }

    @Override
    public void deleteMedicalServiceByName(String medicalServiceName) {
        medicalServiceRepository.deleteByName(medicalServiceName);
    }

    private MedicalService mapToMedicationServiceEntity(MedicalServiceDto medicalServiceDto) {
        return modelMapper.map(medicalServiceDto, MedicalService.class);
    }

    private MedicalServiceDto mapToMedicationServiceDto(MedicalService medicalService) {
        return modelMapper.map(medicalService, MedicalServiceDto.class);
    }
}
