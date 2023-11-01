package com.doctor.controller;

import com.doctor.payload.DoctorDto;
import com.doctor.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorDto> saveDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        return new ResponseEntity<>(doctorService.saveDoctor(doctorDto), HttpStatus.CREATED);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> getDoctorById(@Valid @PathVariable String doctorId) {
        return new ResponseEntity<>(doctorService.getDoctorById(doctorId), HttpStatus.OK);
    }

    @GetMapping("/clinic/{clinicId}")
    public ResponseEntity<List<DoctorDto>> getDoctorByClinicId(@Valid @PathVariable String clinicId) {
        return new ResponseEntity<>(doctorService.getDoctorByClinicId(clinicId), HttpStatus.OK);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<DoctorDto>> searchDoctor(@Valid @RequestParam String keyword) {
//        return new ResponseEntity<>(doctorService.getDoctorsByNameOrSpecialization(keyword), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }

    @PostMapping("/update/{doctorId}")
    public ResponseEntity<DoctorDto> updateDoctor(@Valid @PathVariable String doctorId, @RequestBody DoctorDto doctorDto) {
        return new ResponseEntity<>(doctorService.updateDoctor(doctorId, doctorDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@Valid @PathVariable String doctorId) {
        doctorService.deleteDoctorById(doctorId);
        return new ResponseEntity<>("Doctor record for id " + doctorId + " is deleted successfully.", HttpStatus.OK);
    }
}
