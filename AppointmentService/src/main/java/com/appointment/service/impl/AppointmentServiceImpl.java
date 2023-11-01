package com.appointment.service.impl;

import com.appointment.entity.Appointment;
import com.appointment.enums.TimeSlot;
import com.appointment.exception.ResourceNotFoundException;
import com.appointment.payload.AppointmentDto;
import com.appointment.payload.DoctorDto;
import com.appointment.payload.PatientDto;
import com.appointment.repository.AppointmentRepository;
import com.appointment.service.AppointmentService;
import com.appointment.service.SMSService;
import com.appointment.utils.URLBuilder;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;

    private SMSService smsService;

    private RestTemplate restTemplate;

    private ModelMapper modelMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, SMSService smsService, RestTemplate restTemplate, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.smsService = smsService;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }

    @Override
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        PatientDto patientDto;
        DoctorDto doctorDto;

        try {
            patientDto = restTemplate.getForObject(URLBuilder.PATIENT_SERVICE + "api/patients/" + appointmentDto.getPatientId(), PatientDto.class);
            doctorDto = restTemplate.getForObject(URLBuilder.DOCTOR_SERVICE + "api/doctor/" + appointmentDto.getDoctorId(), DoctorDto.class);
        } catch (HttpClientErrorException ex) {
            String responseBody = ex.getResponseBodyAsString();
            JSONObject jsonObject = new JSONObject(responseBody);
            throw new ResourceNotFoundException(jsonObject.getString("message"), jsonObject.getString("details"));
        }

        appointmentDto.setAppointmentBookingDate(LocalDate.now());
        appointmentDto.setAppointmentBookingTime(LocalTime.now());
        appointmentDto.setId(UUID.randomUUID().toString());

        Appointment appointment = mapToAppointmentEntity(appointmentDto);

        if ("MORNING".equals(appointmentDto.getSelectedTimeSlot())) {
            appointment.setSelectedTimeSlot(TimeSlot.MORNING);
        } else if ("AFTERNOON".equals(appointmentDto.getSelectedTimeSlot())) {
            appointment.setSelectedTimeSlot(TimeSlot.AFTERNOON);
        } else if ("EVENING".equals(appointmentDto.getSelectedTimeSlot())) {
            appointment.setSelectedTimeSlot(TimeSlot.EVENING);
        } else if ("NIGHT".equals(appointmentDto.getSelectedTimeSlot())) {
            appointment.setSelectedTimeSlot(TimeSlot.NIGHT);
        } else {
            // Handle invalid or unknown values
            throw new IllegalArgumentException("Invalid TimeSlot: " + appointmentDto.getSelectedTimeSlot());
        }

        AppointmentDto savedAppointmentDto = mapToAppointmentDto(appointmentRepository.save(appointment));

        smsService.sendSMS(patientDto.getMobile(), generateAppointmentConfirmationMessage(patientDto.getName(), doctorDto.getName()));

        return savedAppointmentDto;
    }

    @Override
    public DoctorDto getAppointsByDoctorId(String doctorId) {
        DoctorDto doctorDto;

        try {
            doctorDto = restTemplate.getForObject(URLBuilder.DOCTOR_SERVICE + "api/doctor/" + doctorId, DoctorDto.class);
        } catch (HttpClientErrorException ex) {
            String responseBody = ex.getResponseBodyAsString();
            JSONObject jsonObject = new JSONObject(responseBody);
            throw new ResourceNotFoundException(jsonObject.getString("message"), jsonObject.getString("details"));
        }

        doctorDto.setListOfAppointments(appointmentRepository.findByDoctorId(doctorId)
                .stream()
                .map(appointment -> mapToAppointmentDto(appointment))
                .collect(Collectors.toList()));

        return doctorDto;
    }

    private Appointment mapToAppointmentEntity(AppointmentDto appointmentDto) {
        return modelMapper.map(appointmentDto, Appointment.class);
    }

    private AppointmentDto mapToAppointmentDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    private String generateAppointmentConfirmationMessage(String patientName, String doctorName) {
        return "Dear " + patientName + ",\n\n" +
                "We are delighted to confirm that your appointment with Dr. " + doctorName + " has been successfully booked. \n\n" +
                "Please remember the following:\n" +
                "- Arrive at least 15 minutes before your appointment time.\n" +
                "- Bring any relevant medical records or test results with you.\n" +
                "Thank you for choosing our healthcare services. We value your trust in us.\n\n" +
                "Warm regards,\n" +
                "[Your Clinic or Hospital Name]";
    }

}
