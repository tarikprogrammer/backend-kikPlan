package com.kikplan.backend.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kikplan.backend.entities.Task;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private String name;
    private String detail;
    private Long userId;
    private Long projectId;
    private String colorTask;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "EN")
    private LocalDate completedAt;
    private String uid;
    private String status;


    public static TaskDto fromEntity(Task task){
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .detail(task.getDetail())
                .userId(task.getUserId())
                .colorTask(generateColor())
                .completedAt(task.getCompletedAt())
                .uid(generateUid())
                .status(task.getStatus())
                .build();
    }
    public static Task toEntity(TaskDto taskDto){
        return Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .detail(taskDto.getDetail())
                .userId(taskDto.getUserId())
                .colorTask(generateColor())
                .completedAt(taskDto.getCompletedAt())
                .uid((generateUid()))
                .status(taskDto.getStatus())
                .build();
    }

    public static String generateColor(){
        List<String>colors= List.of("#C7253E","#7A1CAC","#FF9100","#399918","#FF6969","#FF0000","#2A629A");
        var randomIndex=(int)Math.floor(Math.random()*colors.size());
        return colors.get(randomIndex);
    }

    public static String generateUid(){
        String prefix="TS-";
        Random random=new Random();
        int number=10000+random.nextInt(99999);
        System.out.println(prefix+number);
        return prefix+number;
    }
}
