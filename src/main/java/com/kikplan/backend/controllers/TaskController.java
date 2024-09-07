package com.kikplan.backend.controllers;


import com.kikplan.backend.dto.TaskDto;
import com.kikplan.backend.dto.UserDto;
import com.kikplan.backend.entities.Project;
import com.kikplan.backend.entities.User;
import com.kikplan.backend.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;


    @PostMapping("/addTask")
    public ResponseEntity<TaskDto>save(@RequestBody TaskDto taskDto){
        return ResponseEntity.ok(
                this.taskService.save(taskDto)
        );
    }


    @GetMapping("/getProject/{id}")
    public ResponseEntity<Project>getProject(@PathVariable(name="id") Long id){
        return ResponseEntity.ok(
                taskService.findById(id)
        );
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto>getUser(@PathVariable(name="id") Long id){
        return ResponseEntity.ok(
                taskService.findUser(id)
        );
    }

    @PostMapping("/update")
    public ResponseEntity<TaskDto>getTask(@RequestBody Map<String,String>json){
        return ResponseEntity.ok(
                taskService.updateTask(Long.parseLong(json.get("id")),json.get("status"))
        );
    }
}
