package com.kikplan.backend.services.impl;

import com.kikplan.backend.dto.TeamDto;
import com.kikplan.backend.dto.UserDto;
import com.kikplan.backend.entities.Team;
import com.kikplan.backend.entities.User;
import com.kikplan.backend.repository.TeamRepository;
import com.kikplan.backend.repository.UserRepository;
import com.kikplan.backend.services.TeamService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Override
    public TeamDto save(TeamDto dto) {
        Team team =TeamDto.toEntity(dto);
        return TeamDto.fromEntity(teamRepository.save(team));
    }
    @Override
    public TeamDto findById(Long id) {
        return null;
    }

    @Override
    public List<TeamDto> findAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
/*  USER 1 -----------> team 1 , user 2 -----------> team 1*/
    @Override
    public TeamDto assignUserToTeam(Long teamId, Long userId) {
        Team team=teamRepository.findById(teamId).orElseThrow(()->new EntityNotFoundException("team not found "));
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("user not found "));
        List<Team>teamDtos=user.getTeam();  /*  user=[x,y]*/
        teamDtos.add(team);
        user.setTeam(teamDtos);
        userRepository.save(user);
        return TeamDto.fromEntity(team);
    }
}
