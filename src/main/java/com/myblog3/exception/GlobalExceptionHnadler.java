package com.myblog3.exception;


import com.myblog3.payload.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHnadler extends ResponseEntityExceptionHandler{
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException( ResourceNotFoundEception e,
                                                                         WebRequest webRequest
                                                                         ){
        ErrorDetail errorDetail=new ErrorDetail(e.getMessage(),new Date(),webRequest.getDescription(true));
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
