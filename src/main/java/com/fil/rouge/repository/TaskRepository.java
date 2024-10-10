package com.fil.rouge.repository;

import com.fil.rouge.emuns.Categories;
import com.fil.rouge.emuns.TaskStatus;
import com.fil.rouge.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTitleContainingOrDescriptionContaining(String keyword, String keyword1);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByCategory(Categories category);

    @Query("SELECT a.task FROM Application a WHERE a.worker.id = :workerId")
    List<Task> findTasksByWorkerId(@Param("workerId") Long workerId);
    @Query("SELECT t FROM Task t WHERE t.client.id = :clientId")
    List<Task> findTasksByClientId(@Param("clientId") Long clientId);
    @Query("SELECT COUNT(t) FROM Task t WHERE t.status = :status")
    Long countTasksByStatus(@Param("status") TaskStatus status);
    @Query("SELECT COUNT(t) FROM Task t WHERE t.client.id = :clientId AND t.status = :status")
    Long countTasksByClientAndStatus(@Param("clientId") Long clientId, @Param("status") TaskStatus status);
    @Query("SELECT COUNT(t) FROM Task t")
    Long countAllTasks();
}
