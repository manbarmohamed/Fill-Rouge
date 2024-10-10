package com.fil.rouge.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;           // Used for update, delete, and retrieval
    private Integer rating;    // Used for both creation and update
    private String comment;    // Used for both creation and update
    private Long workerId;     // Used for creation (worker reference)
    //private Long clientId;     // Used for creation (client reference)
    private String workerName; // Used for viewing details with worker information
    private String clientName; // Used for viewing details with client information
}

