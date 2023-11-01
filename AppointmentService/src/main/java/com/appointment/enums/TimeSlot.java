package com.appointment.enums;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@AllArgsConstructor
public enum TimeSlot {
    MORNING("Morning", "09:00 AM - 12:00 PM"),
    AFTERNOON("Afternoon", "12:00 PM - 03:00 PM"),
    EVENING("Evening", "03:00 PM - 06:00 PM"),
    NIGHT("Night", "06:00 PM - 09:00 PM");

    private final String displayName;
    private final String timeRange;

}

