package com.kikplan.backend.controllers;


import com.kikplan.backend.dto.UserDto;
import com.kikplan.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;


    @PostMapping("/")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.save(userDto));
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody Map<String,String> json){

        return ResponseEntity.ok(userService.login(json.get("email"),json.get("password")));
    }

    @PostMapping("/filterByEmail")
    public ResponseEntity<List<UserDto>>filterByEmail(@RequestBody Map<String,String>json){
        return ResponseEntity.ok(userService.filterByEmail(json.get("email")));
    }

    @GetMapping("/find/{userID}")
    public ResponseEntity<UserDto>find(@PathVariable(name = "userID") Long userID){
        return ResponseEntity.ok(
                userService.findById(userID)
        );
    }

    @PostMapping("/changePassword")
    public ResponseEntity<UserDto>changePassword(@RequestBody Map<String,String>json){
        return ResponseEntity.ok(
                userService.changePassword(json.get("email"),json.get("password"),json.get("repassword"))
        );
    }



    @PostMapping("/email")
    public ResponseEntity<UserDto>findByEmail(@RequestBody Map<String,String>json){
        return ResponseEntity.ok(
            userService.findByEmail(json.get("email"))
        );
    }
}
