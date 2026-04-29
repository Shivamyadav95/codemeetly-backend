package org.shivam.codemeetly.service.impl;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.*;
import org.shivam.codemeetly.entity.*;
import org.shivam.codemeetly.repository.*;
import org.shivam.codemeetly.service.ProblemService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepo;
    private final TestCaseRepository testCaseRepo;

    @Override
    public ProblemDto createProblem(String creatorEmail, CreateProblemRequest req) {

        Problem problem = Problem.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .difficulty(Difficulty.valueOf(req.getDifficulty()))
                .constraints(req.getConstraints())
                .author(creatorEmail)
                .createdAt(new Date())
                .build();

        problemRepo.save(problem);

        for (TestCaseDto tc : req.getTestCases()) {
            TestCase testCase = TestCase.builder()
                    .inputData(tc.getInputData())
                    .expectedOutput(tc.getExpectedOutput())
                    .isHidden(tc.isHidden())
                    .problem(problem)
                    .build();

            testCaseRepo.save(testCase);
        }

        return mapToDto(problem);
    }

    @Override
    public List<ProblemDto> getAllProblems() {
        return problemRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public ProblemDto getProblemById(Long id) {
        Problem problem = problemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Problem not found"));
        return mapToDto(problem);
    }

    @Override
    public void deleteProblem(Long id) {
        problemRepo.deleteById(id);
    }

    private ProblemDto mapToDto(Problem problem) {
        ProblemDto dto = new ProblemDto();
        dto.setId(problem.getId());
        dto.setTitle(problem.getTitle());
        dto.setDescription(problem.getDescription());
        dto.setConstraints(problem.getConstraints());
        dto.setDifficulty(problem.getDifficulty().name());
        return dto;
    }
}