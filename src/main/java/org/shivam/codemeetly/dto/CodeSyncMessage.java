package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class CodeSyncMessage {

    private String roomId;
    private String senderEmail;
    private String code;
}