package com.kikplan.backend.repository;

import com.kikplan.backend.entities.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar,Long> {
}
