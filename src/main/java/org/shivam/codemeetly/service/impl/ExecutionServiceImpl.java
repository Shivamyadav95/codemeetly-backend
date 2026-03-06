package org.shivam.codemeetly.service.impl;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.ExecutionRequest;
import org.shivam.codemeetly.dto.ExecutionResponse;
import org.shivam.codemeetly.service.ExecutionService;
import org.shivam.codemeetly.util.CodeExecutor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExecutionServiceImpl implements ExecutionService {

    private final CodeExecutor executor;

    @Override
    public ExecutionResponse execute(ExecutionRequest req) {

        long start = System.currentTimeMillis();
        String container;

        switch (req.getLanguage()) {
            case "java":
                container = "codemeetly-java";
                break;
            case "python":
                container = "codemeetly-python";
                break;
            case "cpp":
                container = "codemeetly-cpp";
                break;
            case "c":
                container = "codemeetly-c";
                break;
            case "js":
                container = "codemeetly-js";
                break;
            default:
                return new ExecutionResponse("Language not supported", 0, 0, "ERROR");
        }

        try {
            String output = executor.runDocker(container, req.getCode(), req.getInput());
            long time = System.currentTimeMillis() - start;

            return new ExecutionResponse(output, time, 0, "SUCCESS");

        } catch (Exception e) {
            return new ExecutionResponse(e.getMessage(), 0, 0, "ERROR");
        }
    }
}