package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class ChatMessageRequest {

    private String sender;
    private String content;   // ✅ use this
    private String roomId;

}