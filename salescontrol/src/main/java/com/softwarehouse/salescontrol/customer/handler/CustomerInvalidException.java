package com.softwarehouse.salescontrol.customer.handler;

public class CustomerInvalidException extends RuntimeException{

    public CustomerInvalidException(String message) {
        super(message);
    }
}
