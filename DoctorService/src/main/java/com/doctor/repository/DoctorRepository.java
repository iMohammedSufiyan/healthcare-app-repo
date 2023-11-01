package com.doctor.repository;

import com.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    //    @Query("SELECT d FROM Doctor d WHERE d.name LIKE %:keyword% OR d.specialization LIKE %:keyword%")
    //    List<Doctor> findByNameOrSpecialization(@Param("keyword") String keyword);
    List<Doctor> findByClinicId(String Id);
}
