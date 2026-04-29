package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class ChatMessage {

    private String roomId;
    private String sender;
    private String message;
    private String timestamp;
}