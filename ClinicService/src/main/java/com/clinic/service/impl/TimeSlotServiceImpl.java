package com.clinic.service.impl;

import com.clinic.entity.TimeSlot;
import com.clinic.exception.ResourceNotFoundException;
import com.clinic.payload.TimeSlotDto;
import com.clinic.repository.TimeSlotRepository;
import com.clinic.service.TimeSlotService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {
    private TimeSlotRepository timeSlotRepository;

    private ModelMapper modelMapper;

    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository, ModelMapper modelMapper) {
        this.timeSlotRepository = timeSlotRepository;
        this.modelMapper = modelMapper;
    }

    public TimeSlotDto saveTimeSlot(TimeSlotDto timeSlotDto) {
        return mapToTimeSlotDto(timeSlotRepository.save(mapToTimeSlotEntity(timeSlotDto)));
    }

    public TimeSlotDto getTimeSlotById(String id) {
        return mapToTimeSlotDto(timeSlotRepository.findById(id).orElseThrow(
                () -> new ResourceAccessException("TimeSlot not found with ID: " + id)
        ));
    }

    public TimeSlotDto updateTimeSlot(String id, TimeSlotDto updatedTimeSlotDto) {
        return null;
    }

    public void deleteTimeSlot(String id) {
        Optional<TimeSlot> timeSlotOptional = timeSlotRepository.findById(id);

        if (timeSlotOptional.isPresent()) {
            timeSlotRepository.delete(timeSlotOptional.get());
        } else {
            throw new ResourceNotFoundException("TimeSlot not found with ID: " + id);
        }
    }

    public List<TimeSlotDto> getAllTimeSlots() {
        List<TimeSlot> timeSlots = timeSlotRepository.findAll();

        return timeSlots.stream()
                .map(timeSlot -> modelMapper.map(timeSlot, TimeSlotDto.class))
                .collect(Collectors.toList());
    }

    private TimeSlot mapToTimeSlotEntity(TimeSlotDto timeSlotDto) {
        return modelMapper.map(timeSlotDto, TimeSlot.class);
    }

    private TimeSlotDto mapToTimeSlotDto(TimeSlot timeSlot) {
        return modelMapper.map(timeSlot, TimeSlotDto.class);
    }
}

