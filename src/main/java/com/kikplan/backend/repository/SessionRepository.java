package com.kikplan.backend.repository;

import com.kikplan.backend.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {
}
