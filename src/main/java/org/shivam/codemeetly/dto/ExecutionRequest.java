package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class ExecutionRequest {

    private String code;
    private String language;
    private String input;   // ✅ FIX ADDED
}