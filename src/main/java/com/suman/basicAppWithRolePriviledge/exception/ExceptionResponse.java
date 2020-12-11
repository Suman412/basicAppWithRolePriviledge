package com.suman.basicAppWithRolePriviledge.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public LocalDateTime responseTime;
    private HttpStatus httpStatus;
    private String errorMessage;
    private String requestedURI;


}