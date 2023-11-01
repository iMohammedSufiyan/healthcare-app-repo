package com.clinic.repository;

import com.clinic.entity.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalServiceRepository extends JpaRepository<MedicalService, String> {
    Optional<MedicalService> findByName(String name);

    void deleteByName(String name);
}
