package org.shivam.codemeetly.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateProblemRequest {

    private String title;
    private String description;
    private String difficulty;
    private String constraints;

    private List<TestCaseDto> testCases;
}