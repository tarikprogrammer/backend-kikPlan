package com.kikplan.backend.controllers;


import com.kikplan.backend.dto.ProjectDto;
import com.kikplan.backend.dto.UserDto;
import com.kikplan.backend.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProjectController {

    private final ProjectService projectService;


    @PostMapping("/members")
    public ResponseEntity<List<UserDto>> memebers(@RequestBody UserDto userDto){
        return ResponseEntity.ok(
                projectService.addMemebre(userDto)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto){
        return ResponseEntity.ok(
                projectService.save(projectDto)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectDto>> projects(){
        return ResponseEntity.ok(
                projectService.findAll()
        );
    }

    @PostMapping("/projectForUser")
    public ResponseEntity<List<ProjectDto>>projectsForUsers(@RequestBody Map<String,String> json){
        return ResponseEntity.ok(projectService.showProjectForUser(json.get("email")));
    }

    @PostMapping("/statistics")
    public ResponseEntity<Map<String,Integer>>ProjectStatistics(@RequestBody Map<String,String> json){
        return ResponseEntity.ok(
                projectService.statistics(json.get("email"))
        );
    }



}
