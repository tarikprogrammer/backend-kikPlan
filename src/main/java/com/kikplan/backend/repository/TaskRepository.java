package com.kikplan.backend.repository;

import com.kikplan.backend.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
   /* List<Task> findByProjectId(Long id);*/
}
