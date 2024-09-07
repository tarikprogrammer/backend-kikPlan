package com.kikplan.backend.dto;


import com.kikplan.backend.entities.Calendar;
import com.kikplan.backend.entities.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CalendarDto {

    private Long id;
    private String event;
    private LocalDate createdAt;
    private LocalDate completedAt;
    private String email;

    public static CalendarDto fromEntity(Calendar calendar){
        return CalendarDto.builder()
                .id(calendar.getId())
                .event(calendar.getEvent())
                .createdAt(calendar.getCreatedAt())
                .completedAt(calendar.getCompletedAt())
                .build();
    }


    public static Calendar toEntity(CalendarDto calendarDto){
        return Calendar.builder()
                .id(calendarDto.getId())
                .event(calendarDto.getEvent())
                .createdAt(calendarDto.getCreatedAt())
                .completedAt(calendarDto.getCompletedAt())
                .build();
    }
}
