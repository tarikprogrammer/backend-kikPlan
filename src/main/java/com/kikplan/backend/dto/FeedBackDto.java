package com.kikplan.backend.dto;


import com.kikplan.backend.entities.FeedBack;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedBackDto {
    private Long id;
    private String name;
    private String email;
    private String message;


    public static FeedBackDto fromEntity(FeedBack feedBack){
        return FeedBackDto.builder()
                .id(feedBack.getId())
                .name(feedBack.getName())
                .email(feedBack.getEmail())
                .message(feedBack.getMessage())
                .build();
    }
    public static FeedBack toEntity(FeedBackDto feedBackDto){
        return FeedBack.builder()
                .id(feedBackDto.getId())
                .name(feedBackDto.getName())
                .email(feedBackDto.getEmail())
                .message(feedBackDto.getMessage())
                .build();
    }
}
