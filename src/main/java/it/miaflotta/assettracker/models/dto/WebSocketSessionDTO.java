package it.miaflotta.assettracker.models.dto;

import lombok.Data;

@Data
public class WebSocketSessionDTO {
    private Long userId;
    private String sessionId;
}
