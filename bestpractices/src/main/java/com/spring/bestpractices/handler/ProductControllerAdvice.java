package com.spring.bestpractices.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@ControllerAdvice(basePackages = "com.spring.bestpractices.controller")
public class ProductControllerAdvice {

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity<ExceptionMessageHandler> entityNotFoundException(EntityNotFoundException entityNotFoundException) {
        ExceptionMessageHandler exceptionMessageHandler = ExceptionMessageHandler
                .builder()
                .message(entityNotFoundException.getMessage())
                .errorCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .build();
        return new ResponseEntity<ExceptionMessageHandler>(exceptionMessageHandler, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity<ExceptionMessageHandler> businesRuleException(BusinesRuleException businesRuleException) {
        ExceptionMessageHandler exceptionMessageHandler = ExceptionMessageHandler
                .builder()
                .message(businesRuleException.getMessage())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .build();
        return new ResponseEntity<ExceptionMessageHandler>(exceptionMessageHandler, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity<ExceptionMessageHandler> invalidArgumentException(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder("| ");
        bindingResult.getFieldErrors().forEach( fieldError ->  {
            stringBuilder.append("Field <" + fieldError.getField() + "> " + fieldError.getDefaultMessage() + " | ");
        });
        ExceptionMessageHandler exceptionMessageHandler = new ExceptionMessageHandler(HttpStatus.BAD_REQUEST.value(), stringBuilder.toString(), new Date());
        return new ResponseEntity<>(exceptionMessageHandler, HttpStatus.BAD_REQUEST);
    }
}
