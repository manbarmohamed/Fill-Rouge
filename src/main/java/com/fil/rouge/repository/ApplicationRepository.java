package com.fil.rouge.repository;

import com.fil.rouge.dto.ApplicationDisplayDto;
import com.fil.rouge.dto.ApplicationDto;
import com.fil.rouge.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findApplicationsByTaskId(Long taskId);
    List<Application> findApplicationsByWorkerId(Long taskId);

}
