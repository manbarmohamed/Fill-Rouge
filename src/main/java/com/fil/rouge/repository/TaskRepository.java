package com.fil.rouge.repository;

import com.fil.rouge.emuns.TaskStatus;
import com.fil.rouge.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTitleContainingOrDescriptionContaining(String keyword, String keyword1);

    List<Task> findByStatus(TaskStatus status);
}
