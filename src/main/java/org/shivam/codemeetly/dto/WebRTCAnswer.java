package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class WebRTCAnswer {
    private String roomId;
    private String sender;
    private String sdp;
}