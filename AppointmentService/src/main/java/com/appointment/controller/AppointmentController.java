package com.appointment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.payload.AppointmentDto;
import com.appointment.payload.DoctorDto;
import com.appointment.service.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/appointment")
@Tag(name = "Book Appointment", description = "Hey, folks come here and book your appoinments😊")
public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @Operation(description = "Appoinment controller", summary = "Create service to book an Appointment")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "201", description = "Appoinment booked", content = {@Content(mediaType = "JSON")}),
    		@ApiResponse(responseCode = "400", description = "Bad request"),
    		@ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Parameters({
    	@Parameter(name = "AppointmentDto",description = "Using whole DTO or POJO to bind with http post method"),
    	@Parameter(name = "BindingResult", description = "Binding result")
    })
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
