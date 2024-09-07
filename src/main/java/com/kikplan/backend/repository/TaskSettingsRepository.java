package com.kikplan.backend.repository;

import com.kikplan.backend.entities.TaskSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskSettingsRepository extends JpaRepository<TaskSettings,Long> {
}
