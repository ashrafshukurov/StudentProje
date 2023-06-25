package com.springboot.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FileNotFoundException extends RuntimeException{
    private final HttpStatus httpStatus=HttpStatus.NOT_FOUND;
    private String message;

}
