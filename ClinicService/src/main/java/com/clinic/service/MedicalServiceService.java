package com.clinic.service;

import com.clinic.payload.MedicalServiceDto;

import java.util.List;

public interface MedicalServiceService {
    MedicalServiceDto saveMedicalService(MedicalServiceDto medicalServiceDto);

    MedicalServiceDto getMedicalServiceById(String medicalServiceId);

    MedicalServiceDto getMedicalServiceByName(String medicalServiceName);

    List<MedicalServiceDto> getAllMedicalServices();

    MedicalServiceDto updateMedicalServiceById(String medicalServiceId, MedicalServiceDto medicalServiceDto);

    void deleteMedicalServiceById(String medicalServiceId);

    void deleteMedicalServiceByName(String medicalServiceName);
}
