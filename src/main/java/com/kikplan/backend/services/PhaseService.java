package com.kikplan.backend.services;

import com.kikplan.backend.dto.PhaseDto;
import com.kikplan.backend.entities.TaskStatus;

public interface PhaseService extends AbstractService<PhaseDto> {
    String generateColor(TaskStatus taskStatus);
    void updatePhase(Long projectId);
}
