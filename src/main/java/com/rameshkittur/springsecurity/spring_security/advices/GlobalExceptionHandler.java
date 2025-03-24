package com.rameshkittur.springsecurity.spring_security.advices;


import com.rameshkittur.springsecurity.spring_security.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException e){
       ApiError apiError = new ApiError(e.getMessage(), HttpStatus.NOT_FOUND);

       return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

}
