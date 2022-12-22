package com.br.vote.config;

import com.br.vote.domain.enums.VoteType;
import com.br.vote.domain.responses.ExceptionResponse;
import com.br.vote.exception.DefaultApiException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class ApiHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(WebExchangeBindException e) {
        var errors = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        var response = ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Ocorreu um ou mais erros na validação da request.")
                .errors(errors)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DefaultApiException.class)
    public ResponseEntity<ExceptionResponse> handleApiException(DefaultApiException e) {
        var response = ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ExceptionResponse> handleEnumException(InvalidFormatException e) {
        var message = e.getTargetType().isAssignableFrom(VoteType.class)
                ? "Opção de voto inválida, as opções de voto disponíveis são: SIM | NAO"
                : e.getMessage();
        var response = ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(message)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
