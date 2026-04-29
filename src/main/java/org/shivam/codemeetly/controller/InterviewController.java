package org.shivam.codemeetly.controller;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.*;
import org.shivam.codemeetly.service.InterviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interview")
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping("/create")
    public InterviewSessionDto create(
            @RequestHeader("email") String interviewerEmail,
            @RequestBody CreateInterviewRequest req) {

        return interviewService.createInterview(interviewerEmail, req);
    }

    @PostMapping("/join/{sessionId}")
    public InterviewSessionDto join(
            @RequestHeader("email") String userEmail,
            @PathVariable Long sessionId) {

        return interviewService.joinInterview(userEmail, sessionId);
    }

    @PostMapping("/end/{sessionId}")
    public InterviewSessionDto end(@PathVariable Long sessionId) {
        return interviewService.endInterview(sessionId);
    }
}