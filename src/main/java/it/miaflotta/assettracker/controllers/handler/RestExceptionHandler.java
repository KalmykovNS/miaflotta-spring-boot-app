package it.miaflotta.assettracker.controllers.handler;

import it.miaflotta.assettracker.exteptions.InvalidInputException;
import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException e) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessages(List.of(e.getMessage()));
        error.setCode(e.getCode());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(InvalidInputException e) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessages(e.getErrorMessages());
        error.setCode(e.getCode());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception e) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessages(List.of(e.getMessage()));
        error.setCode(-1);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
