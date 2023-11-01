package com.patient.service.impl;

import com.patient.entity.Patient;
import com.patient.exception.ResourceNotFoundException;
import com.patient.payload.PatientDto;
import com.patient.repository.PatientRepository;
import com.patient.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    private ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PatientDto savePatient(PatientDto patientDto) {
        patientDto.setId(UUID.randomUUID().toString());
        return mapToDto(patientRepository.save(mapToEntity(patientDto)));
    }

    @Override
    public PatientDto getPatientById(String patientId) {
        return mapToDto(patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException("Patient not found.")
        ));
    }

    @Override
    public List<PatientDto> getAllPatients() {
        return patientRepository
                .findAll()
                .stream()
                .map(patient -> mapToDto(patient))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto updatePatient(String patientId, PatientDto patientDto) {
        return null;
    }

    @Override
    public void removePatient(String patientId) {
        patientRepository.deleteById(patientId);
    }

    private Patient mapToEntity(PatientDto patientDto) {
        return modelMapper.map(patientDto, Patient.class);
    }

    private PatientDto mapToDto(Patient patient) {
        return modelMapper.map(patient, PatientDto.class);
    }
}
