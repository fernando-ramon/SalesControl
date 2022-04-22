package com.spring.bestpractices.handler;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionMessageHandler {
    private Integer errorCode;
    private String message;
    private Date timestamp;
}
