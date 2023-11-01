package com.clinic.service;

import com.clinic.payload.ClinicDto;

public interface ClinicService {
    ClinicDto saveClinic(ClinicDto clinicDto);

    ClinicDto getClinicById(String clinicId);

    ClinicDto getClinicByName(String clinicName);

    ClinicDto updateClinicById(String clinicId, ClinicDto clinicDto);

    void deleteClinicById(String clinicId);
}
