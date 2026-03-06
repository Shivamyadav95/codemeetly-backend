package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class WhiteboardClearEvent {
    private String roomId;
    private String user;
}