package com.fil.rouge.repository;

import com.fil.rouge.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByWorkerId(Long workerId);
}
