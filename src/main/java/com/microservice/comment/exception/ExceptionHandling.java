package com.microservice.comment.exception;

import com.microservice.comment.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorDetails> resourceNotFoundException(
                ResourceNotFoundException ex,
                WebRequest webRequest
                ){
            ErrorDetails errorDetails=new ErrorDetails(
                    webRequest.getDescription(true),
                    ex.getMessage(),
                    new Date()
            );
            return  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionException(
            ResourceNotFoundException ex,
            WebRequest webRequest
    ){
        ErrorDetails errorDetails=new ErrorDetails(
                webRequest.getDescription(true),
                ex.getMessage(),
                new Date()
        );
        return  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
