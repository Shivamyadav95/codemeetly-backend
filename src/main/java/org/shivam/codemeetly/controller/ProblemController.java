package org.shivam.codemeetly.controller;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.CreateProblemRequest;
import org.shivam.codemeetly.dto.ProblemDto;
import org.shivam.codemeetly.service.ProblemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping
    public ProblemDto create(
            @RequestHeader("email") String email,
            @RequestBody CreateProblemRequest request) {

        return problemService.createProblem(email, request);
    }

    @GetMapping
    public List<ProblemDto> getAll() {
        return problemService.getAllProblems();
    }

    @GetMapping("/{id}")
    public ProblemDto getOne(@PathVariable Long id) {
        return problemService.getProblemById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        problemService.deleteProblem(id);
    }
}