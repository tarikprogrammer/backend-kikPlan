package com.kikplan.backend.repository;

import com.kikplan.backend.entities.Project;
import com.kikplan.backend.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Optional<Project> findByPhaseId(Long id);
    Optional<Project> findByTeamId(Long id);
    Optional<Project>findByEmail(String email);
    Optional<Project>findByTasks(List<Task> tasks);
}
