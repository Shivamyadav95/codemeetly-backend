package org.shivam.codemeetly.service;

import org.shivam.codemeetly.dto.*;

public interface InterviewService {

    InterviewSessionDto createInterview(String interviewerEmail, CreateInterviewRequest request);

    InterviewSessionDto joinInterview(String userEmail, Long sessionId);

    InterviewSessionDto endInterview(Long sessionId);
}