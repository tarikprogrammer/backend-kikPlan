package com.kikplan.backend.repository;

import com.kikplan.backend.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*  email ------------------>*/
public interface TeamRepository extends JpaRepository<Team,Long> {

}
