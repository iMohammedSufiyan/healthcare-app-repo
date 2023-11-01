package com.clinic.controller;

import com.clinic.payload.ClinicDto;
import com.clinic.service.ClinicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clinics")
public class ClinicController {
    private ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping
    public ResponseEntity<ClinicDto> saveClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return new ResponseEntity<>(clinicService.saveClinic(clinicDto), HttpStatus.CREATED);
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<ClinicDto> getClinicById(@PathVariable String clinicId) {
        return new ResponseEntity<>(clinicService.getClinicById(clinicId), HttpStatus.OK);
    }

    @GetMapping("/name/{clinicName}")
    public ResponseEntity<ClinicDto> getClinicByName(@PathVariable String clinicName) {
        return new ResponseEntity<>(clinicService.getClinicByName(clinicName), HttpStatus.OK);
    }

}
