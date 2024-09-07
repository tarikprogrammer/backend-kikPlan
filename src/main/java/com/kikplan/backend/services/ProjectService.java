package com.kikplan.backend.services;

import com.kikplan.backend.dto.ProjectDto;
import com.kikplan.backend.dto.UserDto;
import com.kikplan.backend.entities.Project;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProjectService extends AbstractService<ProjectDto> {
    List<UserDto> addMemebre(UserDto userDto);
    ProjectDto findByPhaseId(Long id);
    ProjectDto findByTeamId(Long id);
    List<ProjectDto> showProjectForUser(String email);
    List<ProjectDto>filterProject(String email,String type);
    Map<String,Integer> statistics(String email);


}
