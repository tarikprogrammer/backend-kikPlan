package com.kikplan.backend.controllers;


import com.kikplan.backend.dto.FeedBackDto;
import com.kikplan.backend.entities.FeedBack;
import com.kikplan.backend.services.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class FeedBackController {
    private final FeedBackService feedBackService;


    @PostMapping("/feedBack")
    public ResponseEntity<FeedBackDto>save(@RequestBody FeedBackDto feedBackDto){
        return ResponseEntity.ok(
                feedBackService.save(feedBackDto)
        );
    }
}
