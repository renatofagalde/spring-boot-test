package br.com.likwi.api.controller.exceptions;

import br.com.likwi.api.exception.DataIntegrityViolationException;
import br.com.likwi.api.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultError> objectNotFound(NotFoundException ex, HttpServletRequest request) {

        DefaultError error = new DefaultError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DefaultError> dataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {

        DefaultError error = new DefaultError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
