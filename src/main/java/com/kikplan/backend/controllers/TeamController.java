package com.kikplan.backend.controllers;


import com.kikplan.backend.dto.TeamDto;
import com.kikplan.backend.services.impl.TeamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class TeamController {
    private final TeamServiceImpl teamService;


   /* @PostMapping("/test")
    public ResponseEntity<TeamDto> projectDtoResponseEntity(@RequestBody Map<String,Long> json){
        return ResponseEntity.ok(teamService.assignUserToTeam(json.get("teamID"),json.get("userID")));
    }*/

}
