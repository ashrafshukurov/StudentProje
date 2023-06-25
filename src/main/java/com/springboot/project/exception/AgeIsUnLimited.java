package com.springboot.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@NoArgsConstructor


public class AgeIsUnLimited extends RuntimeException{
    private final HttpStatus httpStatus=HttpStatus.BAD_REQUEST;

    public AgeIsUnLimited(String message) {
        super(message);
    }
}
