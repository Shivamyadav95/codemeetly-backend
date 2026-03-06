package org.shivam.codemeetly.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResult {

    private String verdict;
    private String output;
    private long time;
}