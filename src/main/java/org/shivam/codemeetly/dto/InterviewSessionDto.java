package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class InterviewSessionDto {
    private Long sessionId;
    private String roomId;
    private String interviewerEmail;
    private String candidateEmail;
    private boolean active;
}