package com.softwarehouse.salescontrol.comom;

import com.softwarehouse.salescontrol.customer.handler.CustomerInvalidException;
import com.softwarehouse.salescontrol.customer.handler.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice(basePackages = "com.softwarehouse.salescontrol.customer.controller")
public class ExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity<ExceptionMessageHandler> customerNotFoundException(CustomerNotFoundException customerNotFoundException) {
        ExceptionMessageHandler exceptionMessageHandler = ExceptionMessageHandler
                .builder()
                .message("Customer no found with informed id.")
                .errorCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .build();
        return new ResponseEntity<ExceptionMessageHandler>(exceptionMessageHandler, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity<ExceptionMessageHandler> customerInvalidException(CustomerInvalidException customerInvalidException) {
        ExceptionMessageHandler exceptionMessageHandler = ExceptionMessageHandler
                .builder()
                .message(customerInvalidException.getMessage())
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
        ExceptionMessageHandler exceptionMessageHandler = new ExceptionMessageHandler(new Date(), stringBuilder.toString(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionMessageHandler, HttpStatus.BAD_REQUEST);
    }

}
