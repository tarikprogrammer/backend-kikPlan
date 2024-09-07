package com.kikplan.backend.services;

import com.kikplan.backend.dto.CalendarDto;

public interface CalendarService {
    CalendarDto saveEvent(CalendarDto calendarDto);
}
