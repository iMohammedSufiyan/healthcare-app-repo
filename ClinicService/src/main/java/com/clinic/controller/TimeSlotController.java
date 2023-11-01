package com.clinic.controller;

import com.clinic.payload.TimeSlotDto;
import com.clinic.service.TimeSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/timeSlots")
public class TimeSlotController {
    private TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PostMapping
    public ResponseEntity<TimeSlotDto> saveTimeSlot(@Valid @RequestBody TimeSlotDto timeSlotDto) {
        return new ResponseEntity<>(timeSlotService.saveTimeSlot(timeSlotDto), HttpStatus.CREATED);
    }

    @GetMapping("/{timeSlotId}")
    public ResponseEntity<TimeSlotDto> getTimeSlotById(@PathVariable String timeSlotId) {
        return new ResponseEntity<>(timeSlotService.getTimeSlotById(timeSlotId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TimeSlotDto>> getAllTimeSlots() {
        return new ResponseEntity<>(timeSlotService.getAllTimeSlots(), HttpStatus.OK);
    }

    @PutMapping("/{timeSlotId}")
    public ResponseEntity<TimeSlotDto> updateTimeSlot(
            @PathVariable String timeSlotId,
            @Valid @RequestBody TimeSlotDto updatedTimeSlotDto
    ) {
        return new ResponseEntity<>(timeSlotService.updateTimeSlot(timeSlotId, updatedTimeSlotDto), HttpStatus.OK);
    }

    @DeleteMapping("/{timeSlotId}")
    public ResponseEntity<String> deleteTimeSlot(@PathVariable String timeSlotId) {
        timeSlotService.deleteTimeSlot(timeSlotId);
        return new ResponseEntity<>("TimeSlot with ID " + timeSlotId + " is deleted successfully.", HttpStatus.OK);
    }
}
