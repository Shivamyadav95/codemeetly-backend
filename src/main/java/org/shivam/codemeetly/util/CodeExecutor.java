package org.shivam.codemeetly.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Component
public class CodeExecutor {

    public String runDocker(String containerName, String code, String input) throws Exception {

        ProcessBuilder pb = new ProcessBuilder(
                "docker",
                "run",
                "--rm",
                "-m", "256m",
                "--cpus", "0.5",
                "--network", "none",
                "-i",
                containerName
        );

        Process process = pb.start();

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(process.getOutputStream())
        );

        writer.write(code + "\n<<CODE_END>>\n" + input);
        writer.flush();
        writer.close();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        StringBuilder output = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        boolean finished = process.waitFor(5, TimeUnit.SECONDS);

        if (!finished) {
            process.destroyForcibly();
            return "Execution timed out";
        }

        return output.toString().trim();
    }
}