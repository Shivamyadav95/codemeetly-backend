package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class ChatMessageResponse {
    private Long id;
    private String sender;
    private String message;
    private long timestamp;
}