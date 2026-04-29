package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class CreateInterviewRequest {
    private String roomId;
    private String candidateEmail;
}