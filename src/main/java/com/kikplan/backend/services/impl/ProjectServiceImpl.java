package com.kikplan.backend.services.impl;

import com.kikplan.backend.dto.*;
import com.kikplan.backend.entities.*;
import com.kikplan.backend.repository.ProjectRepository;
import com.kikplan.backend.repository.TeamRepository;
import com.kikplan.backend.repository.UserRepository;
import com.kikplan.backend.services.PhaseService;
import com.kikplan.backend.services.ProjectService;
import com.kikplan.backend.validator.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ObjectValidator<ProjectDto> validator;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final UserServiceImpl userService;
    private final TeamServiceImpl teamService;
    private final PhaseService phaseService;
    private List<UserDto> userDtos = new ArrayList<>();


    @Override
    public ProjectDto save(ProjectDto dto) {
        System.out.println(dto);
        validator.validate(dto);
        TeamDto teamDto = TeamDto.builder()
                .name(dto.getTitle() + " team")
                .size(userDtos.size())
                .users(userDtos)
                .build();

        PhaseDto phaseDto = PhaseDto.builder()
                .name("phase of " + dto.getTitle())
                .status(TaskStatus.TO_DO)
                .color(phaseService.generateColor(TaskStatus.TO_DO))
                .build();
        ProjectDto projectDto = ProjectDto.builder()
                .title(dto.getTitle())
                .email(dto.getEmail())
                .description(dto.getDescription())
                .teamDto(teamDto)
                .phaseDto(phaseDto)
                .build();
        Project project = ProjectDto.toEntity(projectDto);
        Project project1 = projectRepository.save(project);
        Team team1 = project1.getTeam();
        System.out.println(team1);
        userDtos.stream().map(c->{
            if(c.getEmail().equals(project1.getEmail())){
                User userSearch=userRepository.findByEmail(project1.getEmail()).orElseThrow(()->new EntityNotFoundException("user not found "));
                System.out.println("hey i am here");
                List<Notification>notificationDtos=userSearch.getNotification();
                NotificationDto notificationDto=NotificationDto.builder()
                        .name("you have been created a new Project ")
                        .sendTo(project1.getEmail())
                        .isView(false)
                        .build();
                notificationDtos.add(NotificationDto.toEntity(notificationDto));
                userSearch.setNotification(notificationDtos);
                System.out.println("userSearch :"+userSearch);
                System.out.println("updated :"+userRepository.save(userSearch));

            }else{
                User userSearch=userRepository.findByEmail(c.getEmail()).orElseThrow(()->new EntityNotFoundException("user not found "));
                List<Notification>notificationDtos=userSearch.getNotification();
                NotificationDto notificationDto=NotificationDto.builder()
                        .name("you have a new Project ")
                        .sendTo(c.getEmail())
                        .isView(false)
                        .build();
                notificationDtos.add(NotificationDto.toEntity(notificationDto));
                userSearch.setNotification(notificationDtos);
                userRepository.save(userSearch);

            }
            return teamService.assignUserToTeam(team1.getId(),c.getId());
        }).collect(Collectors.toList());


        userDtos = new ArrayList<>();
        return ProjectDto.fromEntity(project1);
    }

    @Override
    public ProjectDto findById(Long id) {
        return projectRepository.findById(id).map(ProjectDto::fromEntity).orElseThrow(() -> new EntityNotFoundException("project was not found with id :" + id));
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream().map(ProjectDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);

    }

    @Override
    public List<UserDto> addMemebre(UserDto userDto) {
        if(!userDtos.contains(userDto)){
            userDtos.add(UserDto.dtoWithoutTeamId(userDto));
        }
        return userDtos;
    }

    @Override
    public ProjectDto findByPhaseId(Long id) {
        return projectRepository.findByPhaseId(id).map(ProjectDto::fromEntity).orElseThrow(() -> new EntityNotFoundException("project with phase was not found "));
    }

    @Override
    public ProjectDto findByTeamId(Long id) {
        return projectRepository.findByTeamId(id).map(ProjectDto::fromEntity).orElseThrow(() -> new EntityNotFoundException("project with phase was not found "));
    }

    @Override
    public List<ProjectDto> showProjectForUser(String email) {
      User userSearch=userRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("user not found with this email"));
      List<Team>teams=userSearch.getTeam();
      List<ProjectDto>projectDtos=new ArrayList<>();
      teams.stream().map(c->
          c.getProjects().stream().map(p->projectDtos.add(ProjectDto.fromEntity(p))).collect(Collectors.toSet())).collect(Collectors.toList());
      return projectDtos;
    }

    @Override
    public List<ProjectDto> filterProject(String email,String type) {
        List<ProjectDto>getProjects=showProjectForUser(email);

        switch (type){
            case "date":

        }
        return List.of();
    }

    @Override
    public Map<String, Integer> statistics(String email) {
        List<ProjectDto> projectDtos=showProjectForUser(email);
        int[] count = {0,0,0,0};
        Map<String,Integer>stats=new HashMap<>();
        projectDtos.stream().map(c->{
            if(c.getPhaseDto().getStatus().equals(TaskStatus.TO_DO)){
                count[0] += 1;
            }
            else if(c.getPhaseDto().getStatus().equals(TaskStatus.In_PROGRESS)){
                count[1] += 1;
            }
            else if(c.getPhaseDto().getStatus().equals(TaskStatus.Testing)){
                count[2] += 1;
            }else{
                count[3] += 1;
            }
            return count;
        }).collect(Collectors.toSet());


        stats.put("todo",count[0]);
        stats.put("InProgress",count[1]);
        stats.put("Testing",count[2]);
        stats.put("Completed",count[3]);

        return stats;
    }


}
