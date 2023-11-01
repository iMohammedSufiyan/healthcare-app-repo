package com.appointment.service;

public interface SMSService {
    String sendSMS(String to, String body);
}