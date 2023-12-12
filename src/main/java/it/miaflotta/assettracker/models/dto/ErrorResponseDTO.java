package it.miaflotta.assettracker.models.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Data
public class ErrorResponseDTO {
    private LocalDateTime dateTime = LocalDateTime.now(ZoneId.systemDefault());
    private int httpStatusCode;
    private List<String> messages;
    private int code;
}