package com.fil.rouge.dto;


import com.fil.rouge.emuns.ApplicationStatus;
import com.fil.rouge.model.Worker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {


    private Long id;

    //private ApplicationStatus status;

    private Long taskId;


    private Long workerId;
}
