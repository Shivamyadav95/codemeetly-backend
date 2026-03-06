package org.shivam.codemeetly.repository;

import org.shivam.codemeetly.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
}