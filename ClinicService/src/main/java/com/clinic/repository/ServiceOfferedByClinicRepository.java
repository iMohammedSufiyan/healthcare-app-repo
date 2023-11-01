package com.clinic.repository;

import com.clinic.entity.ServiceOfferedByClinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceOfferedByClinicRepository extends JpaRepository<ServiceOfferedByClinic, String> {
    List<ServiceOfferedByClinic> findByMedicalServiceId(String medicalServiceId);

    List<ServiceOfferedByClinic> findByClinicId(String clinicId);

    List<ServiceOfferedByClinic> findByDoctorId(String doctorId);
}
