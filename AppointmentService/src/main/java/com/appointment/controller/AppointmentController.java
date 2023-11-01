package com.appointment.controller;

import com.appointment.payload.AppointmentDto;
import com.appointment.payload.DoctorDto;
import com.appointment.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<?> bookAnAppointment(@Valid @RequestBody AppointmentDto appointmentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors (e.g., return error response)
            return ResponseEntity
                    .badRequest()
                    .body(getValidationErrors(bindingResult));
        }
        return new ResponseEntity<>(appointmentService.saveAppointment(appointmentDto), HttpStatus.CREATED);
    }

    private Map<String, String> getValidationErrors(BindingResult bindingResult) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return validationErrors;
    }

    @GetMapping("/appointments/{doctorId}")
    public ResponseEntity<DoctorDto> getAppointmentsByDoctorId(@PathVariable String doctorId) {
        DoctorDto doctorDto = appointmentService.getAppointsByDoctorId(doctorId);

        return new ResponseEntity<>(doctorDto, HttpStatus.OK);
    }
}
