package org.shivam.codemeetly.controller;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.ExecutionRequest;
import org.shivam.codemeetly.dto.ExecutionResponse;
import org.shivam.codemeetly.service.ExecutionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/code")
@RequiredArgsConstructor
public class ExecutionController {

    private final ExecutionService executionService;

    @PostMapping("/run")
    public ExecutionResponse runCode(@RequestBody ExecutionRequest request) {
        return executionService.execute(request);
    }
}