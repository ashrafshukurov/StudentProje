package com.springboot.project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NoSuchElementException extends RuntimeException{
    private final HttpStatus httpStatus=HttpStatus.NOT_FOUND;
    private String message;
}
