package org.shivam.codemeetly.dto;

import lombok.Data;

@Data
public class WhiteboardDrawEvent {
    private String roomId;
    private String user;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private String color;
    private double thickness;
}