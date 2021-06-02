package com.samuellima.cep.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorOutputDto> handleConstraintViolationException(ConstraintViolationException exception) {
        CustomErrorOutputDto customError = new CustomErrorOutputDto();
        String errorMessage = exception.getMessage();
        String messageWithoutField = errorMessage.split(": ")[1];
        customError.setMessage(messageWithoutField);
        return ResponseEntity.badRequest().body(customError);
    }
}