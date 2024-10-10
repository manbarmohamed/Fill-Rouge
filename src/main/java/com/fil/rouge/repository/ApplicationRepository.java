package com.fil.rouge.repository;


import com.fil.rouge.emuns.ApplicationStatus;
import com.fil.rouge.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT a FROM Application a WHERE a.worker.id = :workerId")
    List<Application> findApplicationsByWorkerId(@Param("workerId") Long workerId);
    @Query("SELECT COUNT(a) FROM Application a WHERE a.worker.id = :workerId")
    Long countApplicationsByWorkerId(@Param("workerId") Long workerId);
    @Query("SELECT COUNT(a) FROM Application a")
    Long countAllApplications();
    @Query("SELECT COUNT(a) FROM Application a WHERE a.status = :status")
    Long countApplicationsByStatus(@Param("status") ApplicationStatus status);
    @Query("SELECT a FROM Application a WHERE a.task.id = :taskId")
    List<Application> findApplicationsByTaskId(@Param("taskId") Long taskId);

}
