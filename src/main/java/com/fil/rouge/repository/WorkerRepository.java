package com.fil.rouge.repository;

import com.fil.rouge.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    Worker findByUsername(String name);
    List<Worker> findByNameContainingIgnoreCase(String name);
}
