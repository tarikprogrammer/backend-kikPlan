package com.kikplan.backend.dto;


import com.kikplan.backend.entities.Team;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private Long id;
    private String name;
    private int size;
    private List<UserDto> users=new ArrayList<>();

    public static TeamDto fromEntity(Team team){
        return TeamDto.builder()
                .name(team.getName())
                .size(team.getSize())
                .users(team.getUsers().stream().map(UserDto::fromEntity).collect(Collectors.toList()))
                .build();

    }
    public static Team toEntity(TeamDto dto){
        return Team.builder()
                .name(dto.getName())
                .size(dto.getSize())
                .users(dto.getUsers().stream().map(UserDto::toEntity).collect(Collectors.toList()))
                .build();
    }
}
