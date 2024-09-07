package com.kikplan.backend.controllers;


import com.kikplan.backend.dto.CalendarDto;
import com.kikplan.backend.services.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;



    @PostMapping("/calendar")
    public ResponseEntity<CalendarDto>saveEvent(@RequestBody CalendarDto calendarDto){
        return ResponseEntity.ok(
                calendarService.saveEvent(calendarDto)
        );
    }
}
