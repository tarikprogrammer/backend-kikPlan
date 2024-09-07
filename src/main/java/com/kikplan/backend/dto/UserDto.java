package com.kikplan.backend.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kikplan.backend.entities.Status;
import com.kikplan.backend.entities.Team;
import com.kikplan.backend.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter

public class UserDto {
    private Long id;
    @NotNull(message = "Full name must be not empty")
    @NotBlank(message = "Full name must be not empty")
    private String fullName;
    @NotNull(message = "Email must be not empty")
    @NotBlank(message = "Email name must be not empty")
    @Email(message = "Email must be contain @")
    private String email;
    @NotNull(message = "Phone must be not empty")
    @NotBlank(message = "Phone must be not empty")
    private String phone;
    private String password;
    private String photo;
    private Long teamId;
    private String shortName;
    private String colorProfile;
    private List<NotificationDto>notificationDtos;
    private Status status;

    /*user ->{id ,fullName,password,email,phone}*/

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .photo(user.getPhoto())
                .phone(user.getPhone())
                .password(user.getPassword())
                .shortName(shortFullName(user.getFullName()))
                .colorProfile(user.getColorProfile())
                .notificationDtos(user.getNotification().stream().map(NotificationDto::fromEntity).collect(Collectors.toList()))
                .status(user.getStatus())
                .build();
    }
    public static User toEntity(UserDto userDto){
        return User.builder()
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phone(userDto.getPhone())
                .colorProfile(userDto.getColorProfile())
                .notification(userDto.getNotificationDtos().stream().map(NotificationDto::toEntity).collect(Collectors.toList()))
                .status(userDto.getStatus())
                .build();
    }
    public static UserDto fromEntityWithoutTeamId(User user){
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .photo(user.getPhoto())
                .phone(user.getPhone())
                .password(user.getPassword())
                .shortName(shortFullName(user.getFullName()))
                .colorProfile(user.getColorProfile())
                .notificationDtos(user.getNotification().stream().map(NotificationDto::fromEntity).collect(Collectors.toList()))
                .status(user.getStatus())
                .build();
    }
    public static UserDto dtoWithoutTeamId(UserDto user){
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .photo(user.getPhoto())
                .phone(user.getPhone())
                .password(user.getPassword())
                .shortName(shortFullName(user.getFullName()))
                .colorProfile(user.getColorProfile())
                .notificationDtos(user.getNotificationDtos())
                .status(user.getStatus())
                .build();
    }


    public static String shortFullName(String fullName){
        StringBuilder stringBuilder=new StringBuilder();
        fullName=fullName.toUpperCase();
        stringBuilder.append(fullName.charAt(0));
        for(int i=0;i<fullName.length();i++){
            if(fullName.charAt(i)==' '){
                stringBuilder.append(fullName.charAt(i+1));
            }
        }
        return stringBuilder.toString();
    }
    public static String generateColor(){
        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
        return String.format("#%06x", rand_num);
    }

}
