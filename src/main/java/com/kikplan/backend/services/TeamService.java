package com.kikplan.backend.services;

import com.kikplan.backend.dto.TeamDto;
import com.kikplan.backend.dto.UserDto;

import java.util.List;

public interface TeamService extends AbstractService<TeamDto>{

    TeamDto assignUserToTeam(Long teamId,Long userId);
}
