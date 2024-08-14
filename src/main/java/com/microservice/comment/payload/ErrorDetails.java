package com.microservice.comment.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ErrorDetails {

    private String errorMassage;

    private Date date;

    private String webRequest;

    public ErrorDetails(String errorMassage,String webRequest,Date date) {
        this.webRequest = webRequest;
        this.date=date;
        this.errorMassage=errorMassage;
    }
}
