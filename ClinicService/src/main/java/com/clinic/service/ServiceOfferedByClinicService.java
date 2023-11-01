package com.clinic.service;

import com.clinic.payload.ServiceOfferedByClinicDto;

import java.util.List;

public interface ServiceOfferedByClinicService {
    ServiceOfferedByClinicDto saveServiceOfferedByClinic(ServiceOfferedByClinicDto serviceOfferedByClinicDto);

    ServiceOfferedByClinicDto getServiceOfferedByClinicById(String serviceOfferedId);

    List<ServiceOfferedByClinicDto> getServiceOfferedByClinicByMedicalServiceId(String medicalServiceId);

    List<ServiceOfferedByClinicDto> getServiceOfferedByClinicByClinicId(String clinicId);

    List<ServiceOfferedByClinicDto> getServiceOfferedByClinicByDoctorId(String doctorId);

    ServiceOfferedByClinicDto updateServiceOfferedByClinicById(ServiceOfferedByClinicDto serviceOfferedByClinicDto);

    void deleteServiceOfferedByClinicById(String serviceOfferedByClinicId);
}
