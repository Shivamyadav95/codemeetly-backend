package org.shivam.codemeetly.repository;

import org.shivam.codemeetly.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByUserEmail(String email);

    List<Submission> findByProblemId(Long id);
}