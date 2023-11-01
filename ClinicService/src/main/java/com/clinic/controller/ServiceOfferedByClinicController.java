package com.clinic.controller;

import com.clinic.payload.ServiceOfferedByClinicDto;
import com.clinic.service.ServiceOfferedByClinicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/services-offered-by-clinics")
public class ServiceOfferedByClinicController {
    private ServiceOfferedByClinicService serviceOfferedByClinicService;

    public ServiceOfferedByClinicController(ServiceOfferedByClinicService serviceOfferedByClinicService) {
        this.serviceOfferedByClinicService = serviceOfferedByClinicService;
    }

    @PostMapping
    public ResponseEntity<ServiceOfferedByClinicDto> saveServiceOfferedByClinic(@Valid @RequestBody ServiceOfferedByClinicDto serviceOfferedByClinicDto) {
        return new ResponseEntity<>(serviceOfferedByClinicService.saveServiceOfferedByClinic(serviceOfferedByClinicDto), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{serviceOfferedId}")
    public ResponseEntity<ServiceOfferedByClinicDto> getServiceOfferedByClinicById(@PathVariable String serviceOfferedId) {
        return new ResponseEntity<>(serviceOfferedByClinicService.getServiceOfferedByClinicById(serviceOfferedId), HttpStatus.OK);
    }

    @GetMapping("/getByMedicalServiceId/{medicalServiceId}")
    public ResponseEntity<List<ServiceOfferedByClinicDto>> getServiceOfferedByClinicByMedicalServiceId(@PathVariable String medicalServiceId) {
        return new ResponseEntity<>(serviceOfferedByClinicService.getServiceOfferedByClinicByMedicalServiceId(medicalServiceId), HttpStatus.OK);
    }

    @GetMapping("/getByClinicId/{clinicId}")
    public ResponseEntity<List<ServiceOfferedByClinicDto>> getServiceOfferedByClinicByClinicId(@PathVariable String clinicId) {
        return new ResponseEntity<>(serviceOfferedByClinicService.getServiceOfferedByClinicByMedicalServiceId(clinicId), HttpStatus.OK);
    }

    @GetMapping("/getByDoctorId/{doctorId}")
    public ResponseEntity<List<ServiceOfferedByClinicDto>> getServiceOfferedByClinicByDoctorId(@PathVariable String doctorId) {
        return new ResponseEntity<>(serviceOfferedByClinicService.getServiceOfferedByClinicByDoctorId(doctorId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{serviceOfferedByClinicId}")
    public ResponseEntity<String> deleteServiceOfferedByClinicById(@PathVariable String serviceOfferedByClinicId) {
        serviceOfferedByClinicService.deleteServiceOfferedByClinicById(serviceOfferedByClinicId);
        return new ResponseEntity<>("Service offered by clinic with id: " + serviceOfferedByClinicId + " is deleted successfully.", HttpStatus.OK);
    }
}
