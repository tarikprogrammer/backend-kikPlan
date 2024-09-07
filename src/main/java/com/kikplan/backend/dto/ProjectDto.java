package com.kikplan.backend.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kikplan.backend.entities.Phase;
import com.kikplan.backend.entities.Project;
import com.kikplan.backend.entities.Team;
import com.kikplan.backend.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectDto {
    private Long id;
    @NotEmpty(message = "title must be not empty")
    @NotBlank(message = "title must be not empty")
    private String title;
    @NotEmpty(message = "description must be not empty")
    @NotBlank(message = "description must be not empty")
    private String description;
    private String email;
    private Long teamId;
    private Long phaseId;
    private TeamDto teamDto;
    private PhaseDto phaseDto;
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "dd,MMM yyyy" ,locale ="EN")
    private LocalDate createdAt;
    public static ProjectDto fromEntity(Project project){

        return ProjectDto.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .teamDto(TeamDto.fromEntity(project.getTeam()))
                .phaseDto(PhaseDto.fromEntity(project.getPhase()))
                .teamId(project.getTeam().getId())
                .phaseId(project.getPhase().getId())
                .createdAt(project.getCreatedAt())
                .email(project.getEmail())
                .build();
    }

    public static Project toEntity(ProjectDto projectDto){
        return Project.builder()
                .id(projectDto.getId())
                .title(projectDto.getTitle())
                .description(projectDto.getDescription())
                .team(TeamDto.toEntity(projectDto.getTeamDto()))
                .phase(PhaseDto.toEntity(projectDto.getPhaseDto()))
                .email(projectDto.getEmail())
                .build();

    }
}
