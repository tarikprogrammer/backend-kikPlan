package com.kikplan.backend.controllers;


import com.kikplan.backend.services.PhaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/phase")
public class PhaseController {
    private final PhaseService phaseService;



    @PostMapping("/update")
    public void updatePhase(@RequestBody Map<String,Long>json){
        phaseService.updatePhase(json.get("id"));
    }
}
