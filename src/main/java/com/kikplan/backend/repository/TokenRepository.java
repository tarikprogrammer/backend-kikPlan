package com.kikplan.backend.repository;

import com.kikplan.backend.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,Long> {
}
