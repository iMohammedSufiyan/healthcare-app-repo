package com.doctor.service.impl;

import com.doctor.entity.Doctor;
import com.doctor.exception.ResourceNotFoundException;
import com.doctor.payload.DoctorDto;
import com.doctor.repository.DoctorRepository;
import com.doctor.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;

    private ModelMapper modelMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DoctorDto saveDoctor(DoctorDto doctorDto) {
        doctorDto.setId(UUID.randomUUID().toString());
        return mapToDto(doctorRepository.save(mapToEntity(doctorDto)));
    }

    @Override
    public DoctorDto getDoctorById(String doctorId) {
        return mapToDto(doctorRepository.findById(doctorId).orElseThrow(
                () -> new ResourceNotFoundException("Doctor not found.")
        ));
    }

    @Override
    public List<DoctorDto> getDoctorByClinicId(String clinicId) {
        return doctorRepository.findByClinicId(clinicId).stream()
                .map(doctor -> mapToDto(doctor))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<DoctorDto> getDoctorsByNameOrSpecialization(String keyword) {
//        return doctorRepository
//                .findByNameOrSpecialization(keyword)
//                .stream()
//                .map(doctor -> mapToDto(doctor))
//                .collect(Collectors.toList());
//    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository
                .findAll()
                .stream()
                .map(doctor -> mapToDto(doctor))
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto updateDoctor(String doctorId, DoctorDto doctorDto) {
        return null;
    }

    @Override
    public void deleteDoctorById(String doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    private Doctor mapToEntity(DoctorDto doctorDto) {
        return modelMapper.map(doctorDto, Doctor.class);
    }

    private DoctorDto mapToDto(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDto.class);
    }
}
