package com.springboot.project.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private HttpStatus httpStatus;
    private String errorMessage;
    private LocalDateTime localDateTime;
    private int errorCode;

}
