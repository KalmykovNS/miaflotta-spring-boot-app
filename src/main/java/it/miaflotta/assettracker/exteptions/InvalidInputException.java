package it.miaflotta.assettracker.exteptions;

import lombok.Data;

import java.util.List;

@Data
public class InvalidInputException extends Exception {
    private List<String> errorMessages;
    private int code;

    public InvalidInputException() {
        super();
    }

    public InvalidInputException(List<String> messages) {
        this.errorMessages = messages;
        this.code = -1;
    }

    public InvalidInputException(List<String> messages, int code) {
        this.errorMessages = messages;
        this.code = code;
    }
}