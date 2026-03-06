package org.shivam.codemeetly.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long problemId;

    private String userEmail;

    private String language;

    @Column(columnDefinition = "TEXT")
    private String code;

    private String verdict;

    private long time;
}