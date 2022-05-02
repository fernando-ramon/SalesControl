package com.softwarehouse.salescontrol.comom;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ExceptionMessageHandler {
    private Date timestamp;
    private String message;
    private Integer errorCode;
}
