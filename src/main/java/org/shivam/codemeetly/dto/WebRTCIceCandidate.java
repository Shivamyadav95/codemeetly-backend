package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class WebRTCIceCandidate {
    private String roomId;
    private String sender;
    private String candidate;
    private String sdpMid;
    private int sdpMLineIndex;
}