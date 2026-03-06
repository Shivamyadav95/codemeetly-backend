package org.shivam.codemeetly.service;

import org.shivam.codemeetly.dto.*;

public interface SubmissionService {

    SubmissionResult submit(String userEmail, SubmissionRequest request);
}