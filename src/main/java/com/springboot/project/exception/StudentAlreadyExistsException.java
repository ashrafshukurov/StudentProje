package com.springboot.project.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter

public class StudentAlreadyExistsException extends RuntimeException{
    private final HttpStatus httpStatus=HttpStatus.CONFLICT;
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}
