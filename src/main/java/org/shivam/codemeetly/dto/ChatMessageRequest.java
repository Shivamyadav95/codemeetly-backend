package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class ChatMessageRequest {
    private String roomId;
    private String sender;
    private String message;
}