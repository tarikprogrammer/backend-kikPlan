package com.kikplan.backend.controllers;


import com.kikplan.backend.services.InviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class InviteController {
    private final InviteService inviteService;


    @PostMapping("/invite")
    public void invite(@RequestBody Map<String,String> json){
        this.inviteService.invite(json.get("email"));
    }
}
