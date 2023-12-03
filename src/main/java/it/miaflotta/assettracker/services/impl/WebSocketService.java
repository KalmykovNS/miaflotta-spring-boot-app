package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.models.dto.WebSocketSessionDTO;
import it.miaflotta.assettracker.services.IWebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebSocketService implements IWebSocketService {
    @Override
    public void registerSession(WebSocketSessionDTO session) {

    }

    @Override
    public void removeSession(Long id) {

    }
}
