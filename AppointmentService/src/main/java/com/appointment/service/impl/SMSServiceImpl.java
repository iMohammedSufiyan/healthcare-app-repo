package com.appointment.service.impl;

import com.appointment.service.SMSService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSServiceImpl implements SMSService {
    @Value("${twilio.account.sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Override
    public String sendSMS(String to, String body) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber("+18504625460"),
                body
        ).create();

        if (message.getStatus() == Message.Status.SENT || message.getStatus() == Message.Status.DELIVERED) {
            return "SMS was successfully sent and/or delivered.";
        } else {
            return "SMS failed to send or deliver. Status: " + message.getStatus();
        }
    }
}
