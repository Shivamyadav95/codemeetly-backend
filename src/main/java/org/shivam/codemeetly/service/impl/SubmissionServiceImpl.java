package org.shivam.codemeetly.service.impl;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.*;
import org.shivam.codemeetly.entity.*;
import org.shivam.codemeetly.repository.*;
import org.shivam.codemeetly.service.ExecutionService;
import org.shivam.codemeetly.service.SubmissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepo;
    private final ProblemRepository problemRepo;
    private final ExecutionService executionService;

    @Override
    public SubmissionResult submit(String userEmail, SubmissionRequest req) {

        Problem problem = problemRepo.findById(req.getProblemId())
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        List<TestCase> testCases = problem.getTestCases();

        boolean allPassed = true;
        long totalTime = 0;
        String finalOutput = "";

        for (TestCase tc : testCases) {

            ExecutionRequest execReq = new ExecutionRequest();
            execReq.setLanguage(req.getLanguage());
            execReq.setCode(req.getCode());
            execReq.setInput(tc.getInputData());

            ExecutionResponse execRes = executionService.execute(execReq);

            totalTime += execRes.getTime();

            if (!execRes.getOutput().trim().equals(tc.getExpectedOutput().trim())) {
                allPassed = false;
                finalOutput = execRes.getOutput();
                break;
            }

            finalOutput = execRes.getOutput();
        }

        Submission submission = Submission.builder()
                .problemId(req.getProblemId())
                .userEmail(userEmail)
                .language(req.getLanguage())
                .code(req.getCode())
                .verdict(allPassed ? "PASS" : "FAIL")
                .time(totalTime)
                .build();

        submissionRepo.save(submission);

        return new SubmissionResult(
                allPassed ? "PASS" : "FAIL",
                finalOutput,
                totalTime
        );
    }
}