package com.kikplan.backend.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kikplan.backend.entities.Notification;
import com.kikplan.backend.entities.User;
import com.kikplan.backend.services.impl.UserServiceImpl;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NotificationDto {
    private Long id;
    private String sendTo;
    private String name;
    private Boolean isView;
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "dd,MMM yyyy" ,locale ="EN")
    private LocalDate createdAt;




    public static NotificationDto fromEntity(Notification notification){
        return NotificationDto.builder()
                .id(notification.getId())
                .name(notification.getName())
                .sendTo(notification.getSendTo())
                .isView(notification.getIsView())
                .createdAt(notification.getCreatedAt())
                .build();
    }

    public static Notification toEntity(NotificationDto notificationDto){
        return Notification.builder()
                .id(notificationDto.getId())
                .name(notificationDto.getName())
                .sendTo(notificationDto.getSendTo())
                .isView(notificationDto.getIsView())
                .createdAt(notificationDto.getCreatedAt())
                .build();
    }
}
