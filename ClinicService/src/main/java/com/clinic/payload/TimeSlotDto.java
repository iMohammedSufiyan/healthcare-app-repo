package com.clinic.payload;

import javax.validation.constraints.*;
import java.time.*;

public class TimeSlotDto {
    private String id;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "Day of the week is required")
    private DayOfWeek dayOfWeek;
}
