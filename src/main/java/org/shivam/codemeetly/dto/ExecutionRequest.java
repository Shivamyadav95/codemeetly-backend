package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class ExecutionRequest {
    private String language;
    private String code;
    private String input;
}