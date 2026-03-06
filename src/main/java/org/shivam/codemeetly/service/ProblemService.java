package org.shivam.codemeetly.service;

import org.shivam.codemeetly.dto.*;
import java.util.List;

public interface ProblemService {

    ProblemDto createProblem(String creatorEmail, CreateProblemRequest request);

    List<ProblemDto> getAllProblems();

    ProblemDto getProblemById(Long id);

    void deleteProblem(Long id);
}