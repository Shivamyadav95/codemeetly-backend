package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class ProblemDto {
    private Long id;
    private String title;
    private String description;
    private String difficulty;
    private String constraints;
}