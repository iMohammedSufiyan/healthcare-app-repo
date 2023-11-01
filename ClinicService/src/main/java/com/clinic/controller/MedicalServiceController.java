package com.clinic.controller;

import com.clinic.payload.MedicalServiceDto;
import com.clinic.service.MedicalServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/medical-services")
public class MedicalServiceController {
    private MedicalServiceService medicalServiceService;

    public MedicalServiceController(MedicalServiceService medicalServiceService) {
        this.medicalServiceService = medicalServiceService;
    }

    @PostMapping
    public ResponseEntity<MedicalServiceDto> saveMedicalService(@Valid @RequestBody MedicalServiceDto medicalServiceDto) {
        return new ResponseEntity<>(medicalServiceService.saveMedicalService(medicalServiceDto), HttpStatus.CREATED);
    }

    @GetMapping("/id/{medicalServiceId}")
    public ResponseEntity<MedicalServiceDto> getMedicalServiceById(@PathVariable String medicalServiceId) {
        return new ResponseEntity<>(medicalServiceService.getMedicalServiceById(medicalServiceId), HttpStatus.OK);
    }

    @GetMapping("/name/{medicalServiceName}")
    public ResponseEntity<MedicalServiceDto> getMedicalServiceByName(@PathVariable String medicalServiceName) {
        return new ResponseEntity<>(medicalServiceService.getMedicalServiceByName(medicalServiceName), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MedicalServiceDto>> getAllMedicalServices() {
        return new ResponseEntity<>(medicalServiceService.getAllMedicalServices(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{medicalServiceId}")
    public ResponseEntity<String> deleteMedicalServiceById(@PathVariable String medicalServiceId) {
        medicalServiceService.deleteMedicalServiceById(medicalServiceId);
        return new ResponseEntity<>("Medical service with id + " + medicalServiceId + " is deleted successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/deleteByName/{medicalServiceName}")
    public ResponseEntity<String> deleteMedicalServiceByName(@PathVariable String medicalServiceName) {
        medicalServiceService.deleteMedicalServiceByName(medicalServiceName);
        return new ResponseEntity<>("Medical service + " + medicalServiceName + " is deleted successfully.", HttpStatus.OK);
    }
}
