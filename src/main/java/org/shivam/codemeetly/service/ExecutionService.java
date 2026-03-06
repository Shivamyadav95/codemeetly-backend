package org.shivam.codemeetly.service;

import org.shivam.codemeetly.dto.ExecutionRequest;
import org.shivam.codemeetly.dto.ExecutionResponse;

public interface ExecutionService {
    ExecutionResponse execute(ExecutionRequest request);
}