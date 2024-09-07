package com.kikplan.backend.services.impl;


import com.kikplan.backend.dto.PhaseDto;
import com.kikplan.backend.dto.ProjectDto;
import com.kikplan.backend.entities.Phase;
import com.kikplan.backend.entities.Project;
import com.kikplan.backend.entities.Task;
import com.kikplan.backend.entities.TaskStatus;
import com.kikplan.backend.repository.PhaseRepository;
import com.kikplan.backend.repository.ProjectRepository;
import com.kikplan.backend.repository.TaskRepository;
import com.kikplan.backend.services.PhaseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhaseServiceImpl implements PhaseService {
    private final PhaseRepository phaseRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Override
    public PhaseDto save(PhaseDto dto) {
        Phase phase =PhaseDto.toEntity(dto);
        return PhaseDto.fromEntity(phaseRepository.save(phase));
    }

    @Override
    public PhaseDto findById(Long id) {
        return null;
    }

    @Override
    public List<PhaseDto> findAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public String generateColor(TaskStatus taskStatus) {
        String color ="";
        switch (taskStatus){
            case TO_DO:
                color="#FF4C4C";
                break;
            case In_PROGRESS:
                color="#FF8343";
                break;
            case Testing:
                color="#EF5A6F";
                break;
            case DONE:
                color="#399918";
                break;
            default:
                break;
        }
        return color;
    }

    @Override
    public void updatePhase(Long projectId) {


        Project project=projectRepository.findById(projectId).orElseThrow(()->new EntityNotFoundException("not found "));



        List<Task>tasks=project.getTasks();
        int countToDo = 0;
        int countInProgress = 0;
        int countTesting = 0;
        int countDone = 0;


        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).getStatus().equals("To DO")){
                countToDo+=1;
            }
            if(tasks.get(i).getStatus().equals("In Progress")){
                countInProgress+=1;
            }
            if(tasks.get(i).getStatus().equals("Testing")){
                countTesting+=1;
            }
            if(tasks.get(i).getStatus().equals("Completed")){
                countDone+=1;
            }


        }



        String status;

       if(tasks==null){
           status = "TO_DO";
       }
        if (countToDo!=0) {
            status = "TO_DO";
        } else if (countInProgress!=0) {
            status = "In_Progress";
        } else if (countTesting!=0) {
            status = "Testing";
        } else{
            status = "Done";
        }


        switch (status) {
            case "TO_DO":
                project.getPhase().setStatus(TaskStatus.TO_DO);
                project.getPhase().setColor(generateColor(TaskStatus.TO_DO));
                break;
            case "In_Progress":
                project.getPhase().setStatus(TaskStatus.In_PROGRESS);
                project.getPhase().setColor(generateColor(TaskStatus.In_PROGRESS));
                break;
            case "Testing":
                project.getPhase().setStatus(TaskStatus.Testing);
                project.getPhase().setColor(generateColor(TaskStatus.Testing));
                break;
            case "Done":
                project.getPhase().setStatus(TaskStatus.DONE);
                project.getPhase().setColor(generateColor(TaskStatus.DONE));
                break;
            default:

                break;
        }


        projectRepository.save(project);
    }

}
