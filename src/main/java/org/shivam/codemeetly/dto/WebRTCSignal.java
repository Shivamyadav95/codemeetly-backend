package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class WebRTCSignal {

    private String type;


    private WebRTCOffer offer;
    private WebRTCAnswer answer;
    private WebRTCIceCandidate iceCandidate;
}