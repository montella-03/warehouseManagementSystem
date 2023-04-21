package com.warehouse.sales.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Error {
    //error handling
    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail problemDetail(RuntimeException exception){
        return ProblemDetail.forStatusAndDetail
                (HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
