package org.shivam.codemeetly.controller;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.*;
import org.shivam.codemeetly.service.SubmissionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public SubmissionResult submit(
            @RequestHeader("email") String email,
            @RequestBody SubmissionRequest request) {

        return submissionService.submit(email, request);
    }
}