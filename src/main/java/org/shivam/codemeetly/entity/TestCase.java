package org.shivam.codemeetly.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "test_cases")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputData;

    private String expectedOutput;

    private boolean isHidden;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;
}