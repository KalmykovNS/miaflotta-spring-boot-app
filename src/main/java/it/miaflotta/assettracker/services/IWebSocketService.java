package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.models.dto.WebSocketSessionDTO;

public interface IWebSocketService {
    void registerSession(WebSocketSessionDTO session);
    void removeSession(Long id);
}
