package com.kikplan.backend.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AllTaskDto {
    private Long id;
    private String phase;
    private TaskDto taskDto;
}
