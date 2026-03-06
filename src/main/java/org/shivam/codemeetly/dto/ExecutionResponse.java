package org.shivam.codemeetly.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionResponse {
    private String output;
    private long time;
    private long memory;
    private String status;
}