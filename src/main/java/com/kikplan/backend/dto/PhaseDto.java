package com.kikplan.backend.dto;


import com.kikplan.backend.entities.Phase;
import com.kikplan.backend.entities.Project;
import com.kikplan.backend.entities.TaskStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PhaseDto {

    private Long id;
    private String name;
    private TaskStatus status;
    private Long projectId;
    private String color;

    public static PhaseDto fromEntity(Phase phase){
        return PhaseDto.builder()
                .name(phase.getName())
                .status(phase.getStatus())
                .color(phase.getColor())
                .build();
    }


    public static Phase toEntity(PhaseDto phaseDto){

        return Phase.builder()
                .name(phaseDto.getName())
                .status(phaseDto.getStatus())
                .color(phaseDto.getColor())
                .build();
    }
}
