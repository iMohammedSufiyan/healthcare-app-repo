package com.appointment.controller;

import com.appointment.payload.SMSRequest;
import com.appointment.service.SMSService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SMSController {
    private SMSService smsService;

    public SMSController(SMSService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/send-sms")
    public String sendSMS(@RequestBody SMSRequest smsRequest) {
        return smsService.sendSMS(smsRequest.getTo(), smsRequest.getMessage());
    }
}