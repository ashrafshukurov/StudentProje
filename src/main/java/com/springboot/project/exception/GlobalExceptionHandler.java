package com.springboot.project.exception;

import com.springboot.project.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
@Slf4j

public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> noSuchHandler(NoSuchElementException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorCode(ex.getHttpStatus().value());
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setLocalDateTime(LocalDateTime.now());
        return  ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }
    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<?> alreadyExists(StudentAlreadyExistsException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorCode(ex.getHttpStatus().value());
        errorResponse.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }
    @ExceptionHandler(AgeIsUnLimited.class)
    public ResponseEntity<?> unLimitedAge(AgeIsUnLimited ex){
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> fileNtFound(FileNotFoundException fileEx){
        return ResponseEntity.status(fileEx.getHttpStatus()).body(fileEx.getMessage());

    }


}
