package io.github.joaofranciscoms.devitrine.infra.handlers;

import io.github.joaofranciscoms.devitrine.common.exceptions.ComentarioNaoEncontradoException;
import io.github.joaofranciscoms.devitrine.common.exceptions.PostNaoEncontradoException;
import io.github.joaofranciscoms.devitrine.common.exceptions.UnauthorizedException;
import io.github.joaofranciscoms.devitrine.common.exceptions.ValidationException;
import io.github.joaofranciscoms.devitrine.common.validation.CampoInvalido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException e){
        var status = HttpStatus.UNPROCESSABLE_CONTENT;
        var body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", e.getMessage(),
                "camposInvalidos", e.getCamposInvalidos()
        );

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        var camposInvalidos = e.getFieldErrors()
                .stream()
                .map(fe -> new CampoInvalido(fe.getField(), fe.getDefaultMessage() ))
                .toList();

        var status = HttpStatus.UNPROCESSABLE_CONTENT;

        var body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", e.getMessage(),
                "camposInvalidos", camposInvalidos
        );

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(ComentarioNaoEncontradoException.class)
    public ResponseEntity<?> handleRegistroNaoEncontradoException(
            ComentarioNaoEncontradoException e){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", e.getMessage(),
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(PostNaoEncontradoException.class)
    public ResponseEntity<?> handleRegistroNaoEncontradoException(
            PostNaoEncontradoException e){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", e.getMessage(),
                        "message", e.getMessage()
                ));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(
            ResponseStatusException e){

        return ResponseEntity
                .status(e.getStatusCode())
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", e.getStatusCode(),
                        "error", e.getMessage(),
                        "message", e.getMessage()
                ));
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(
            UnauthorizedException e){

        var status = HttpStatus.UNAUTHORIZED;

        return ResponseEntity
                .status(status)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", status.value(),
                        "error", e.getMessage(),
                        "message", e.getMessage()
                ));
    }
}
