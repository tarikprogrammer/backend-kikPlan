package com.kikplan.backend.repository;

import com.kikplan.backend.entities.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBack,Long> {
}
