package com.patient.service;

import com.patient.payload.PatientDto;

import java.util.List;

public interface PatientService {
    PatientDto savePatient(PatientDto patientDto);

    PatientDto getPatientById(String patientId);

    List<PatientDto> getAllPatients();

    PatientDto updatePatient(String patientId, PatientDto patientDto);

    void removePatient(String patientId);
}
