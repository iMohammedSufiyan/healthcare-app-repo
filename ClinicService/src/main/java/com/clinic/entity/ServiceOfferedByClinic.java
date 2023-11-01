package com.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "services_offered_by_clinics")
public class ServiceOfferedByClinic {
    @Id
    private String id;

    @Column(nullable = false)
    private String medicalServiceId;

    @Column(nullable = false)
    private String clinicId;

    @Column(nullable = false)
    private String doctorId;
}
