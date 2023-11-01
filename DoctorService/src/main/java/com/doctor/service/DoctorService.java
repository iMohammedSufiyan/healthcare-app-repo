package com.doctor.service;

import com.doctor.payload.DoctorDto;

import java.util.List;

public interface DoctorService {
    DoctorDto saveDoctor(DoctorDto doctorDto);

    DoctorDto getDoctorById(String doctorId);

    List<DoctorDto> getDoctorByClinicId(String clinicId);

//    List<DoctorDto> getDoctorsByNameOrSpecialization(String keyword);

    List<DoctorDto> getAllDoctors();

    DoctorDto updateDoctor(String doctorId, DoctorDto doctorDto);

    void deleteDoctorById(String doctorId);
}
