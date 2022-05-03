package com.softwarehouse.salescontrol.comom;

import com.softwarehouse.salescontrol.customer.handler.CustomerInvalidException;
import com.softwarehouse.salescontrol.customer.handler.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExceptionHandlerAdviceTest {

    ExceptionHandlerAdvice exceptionHandlerAdvice;

    @BeforeEach
    public void init() {
        exceptionHandlerAdvice = new ExceptionHandlerAdvice();
    }

    @Test
    public void customerNotFoundExceptionTest() {
        ResponseEntity<ExceptionMessageHandler> responseEntity = exceptionHandlerAdvice.customerNotFoundException(new CustomerNotFoundException());
        assertNotNull(responseEntity.getBody());
        assertEquals(responseEntity.getBody().getErrorCode(), HttpStatus.NOT_FOUND.value());
        assertEquals(responseEntity.getBody().getMessage(), "Customer no found with informed id.");
    }

    @Test
    public void customerInvalidExceptionTest() {
        ResponseEntity<ExceptionMessageHandler> responseEntity = exceptionHandlerAdvice.customerInvalidException(new CustomerInvalidException("Customer is invalid."));
        assertNotNull(responseEntity.getBody());
        assertEquals(responseEntity.getBody().getErrorCode(), HttpStatus.BAD_REQUEST.value());
        assertEquals(responseEntity.getBody().getMessage(), "Customer is invalid.");
    }
}
