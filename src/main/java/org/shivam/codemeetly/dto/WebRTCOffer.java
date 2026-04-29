package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class WebRTCOffer {
    private String roomId;
    private String sender;
    private String sdp;
}