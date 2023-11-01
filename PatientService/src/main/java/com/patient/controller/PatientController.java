package com.patient.controller;

import com.patient.payload.PatientDto;
import com.patient.service.PatientService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientDto> savePatient(@Valid @RequestBody PatientDto patientDto) {
        return new ResponseEntity<>(patientService.savePatient(patientDto), HttpStatus.CREATED);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> getPatientById(@Valid @PathVariable String patientId) {
        return new ResponseEntity<>(patientService.getPatientById(patientId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatient() {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }

    @PostMapping("/update/{patientId}")
    public ResponseEntity<PatientDto> updatePatient(@Valid @PathVariable String patientId, @RequestBody PatientDto patientDto) {
        return new ResponseEntity<>(patientService.updatePatient(patientId, patientDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{patientId}")
    public ResponseEntity<String> removePatient(@Valid @PathVariable String patientId) {
        patientService.removePatient(patientId);
        return new ResponseEntity<>("Patient deleted.", HttpStatus.OK);
    }
}
