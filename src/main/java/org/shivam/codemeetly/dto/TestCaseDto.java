package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class TestCaseDto {
    private Long id;
    private String inputData;
    private String expectedOutput;
    private boolean hidden;
}