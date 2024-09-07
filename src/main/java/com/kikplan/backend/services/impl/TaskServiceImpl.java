package com.kikplan.backend.services.impl;

import com.kikplan.backend.dto.TaskDto;
import com.kikplan.backend.dto.UserDto;
import com.kikplan.backend.entities.Project;
import com.kikplan.backend.entities.Task;
import com.kikplan.backend.repository.ProjectRepository;
import com.kikplan.backend.repository.TaskRepository;
import com.kikplan.backend.repository.UserRepository;
import com.kikplan.backend.services.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public TaskDto save(TaskDto taskDto) {
        Project projectSearch=projectRepository.findById(taskDto.getProjectId()).orElseThrow(()->new EntityNotFoundException("project not found "));
        if(projectSearch.getTasks()==null){
            List<Task>tasks=new ArrayList<>();
            tasks.add(TaskDto.toEntity(taskDto));
            projectSearch.setTasks(tasks);
            Project project=projectRepository.save(projectSearch);
            Task task=project.getTasks().get(project.getTasks().size()-1);
            taskDto=TaskDto.fromEntity(task);

        }else{
            List<Task>tasks=projectSearch.getTasks();
            tasks.add(TaskDto.toEntity(taskDto));
            projectSearch.setTasks(tasks);
            Project project=projectRepository.save(projectSearch);
            Task task=project.getTasks().get(project.getTasks().size()-1);
            taskDto=TaskDto.fromEntity(task);

        }
        return taskDto;
    }

    @Override
    public List<TaskDto> findAllTaskByProjectId(Long projectId) {
        return List.of();
    }

    @Override
    public Project findById(Long id) {
        return  projectRepository.findById(id).orElseThrow(()->new EntityNotFoundException("project Not found "));


    }

    @Override
    public UserDto findUser(Long id) {

        return UserDto.fromEntityWithoutTeamId(userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("user not found ")));
    }

    @Override
    public TaskDto updateTask(Long id,String status) {
        Task task=taskRepository.findById(id).orElseThrow(()->new EntityNotFoundException("task not found "));
        task.setStatus(status);
        return TaskDto.fromEntity(taskRepository.save(task));
    }
}
