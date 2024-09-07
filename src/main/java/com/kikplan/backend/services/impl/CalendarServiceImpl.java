package com.kikplan.backend.services.impl;

import com.kikplan.backend.dto.CalendarDto;
import com.kikplan.backend.entities.Calendar;
import com.kikplan.backend.entities.User;
import com.kikplan.backend.repository.CalendarRepository;
import com.kikplan.backend.repository.UserRepository;
import com.kikplan.backend.services.CalendarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {
    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;



    @Override
    public CalendarDto saveEvent(CalendarDto calendarDto) {
        User userSearch =userRepository.findByEmail(calendarDto.getEmail()).orElseThrow(()->new EntityNotFoundException("user not found "));
        Calendar calendar=CalendarDto.toEntity(calendarDto);

        if(userSearch.getCalendars()==null){
            List<CalendarDto> calendarDtoList=new ArrayList<>();
            calendarDtoList.add(calendarDto);
            userSearch.setCalendars(calendarDtoList.stream().map(CalendarDto::toEntity).collect(Collectors.toList()));
            userRepository.save(userSearch);
        }else{
            List<Calendar>calendarDtoList=userSearch.getCalendars();
            calendarDtoList.add(calendar);
            userSearch.setCalendars(calendarDtoList);
            userRepository.save(userSearch);
        }
        return calendarDto;
    }
}
