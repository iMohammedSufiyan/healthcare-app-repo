package com.clinic.service;

import com.clinic.payload.TimeSlotDto;

import java.util.List;

public interface TimeSlotService {

    TimeSlotDto saveTimeSlot(TimeSlotDto timeSlotDto);

    TimeSlotDto getTimeSlotById(String id);

    TimeSlotDto updateTimeSlot(String id, TimeSlotDto updatedTimeSlotDto);

    void deleteTimeSlot(String id);

    List<TimeSlotDto> getAllTimeSlots();
}
