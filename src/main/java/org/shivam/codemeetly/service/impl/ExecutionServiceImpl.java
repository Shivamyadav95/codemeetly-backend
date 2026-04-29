package org.shivam.codemeetly.service.impl;

import org.shivam.codemeetly.dto.ExecutionRequest;
import org.shivam.codemeetly.dto.ExecutionResponse;
import org.shivam.codemeetly.service.ExecutionService;
import org.springframework.stereotype.Service;

@Service
public class ExecutionServiceImpl implements ExecutionService {

    @Override
    public ExecutionResponse execute(ExecutionRequest request) {

        long start = System.currentTimeMillis();

        String code = request.getCode();
        String language = request.getLanguage();
        String input = request.getInput();

        // 🔥 Dummy execution logic
        String output;

        if (language.equalsIgnoreCase("java")) {
            output = "Executed Java with input: " + input;
        } else if (language.equalsIgnoreCase("python")) {
            output = "Executed Python with input: " + input;
        } else {
            output = "Unsupported language";
        }

        long end = System.currentTimeMillis();

        return new ExecutionResponse(output, end - start);
    }
}