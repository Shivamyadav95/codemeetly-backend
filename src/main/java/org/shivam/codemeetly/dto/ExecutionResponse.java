package org.shivam.codemeetly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExecutionResponse {

    private String output;
    private long time;
}