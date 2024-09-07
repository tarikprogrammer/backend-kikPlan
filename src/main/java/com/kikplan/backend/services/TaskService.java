package com.kikplan.backend.services;

import com.kikplan.backend.dto.TaskDto;
import com.kikplan.backend.dto.UserDto;
import com.kikplan.backend.entities.Project;
import com.kikplan.backend.entities.User;

import java.util.List;

public interface TaskService {
    TaskDto save(TaskDto taskDto);
    List<TaskDto>findAllTaskByProjectId(Long projectId);
    Project findById(Long id);
    UserDto findUser(Long id);
     TaskDto updateTask(Long id,String status);
}
