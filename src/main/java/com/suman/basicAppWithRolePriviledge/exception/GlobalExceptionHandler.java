package com.suman.basicAppWithRolePriviledge.exception;


import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
@Priority(2)
@Slf4j
public class GlobalExceptionHandler {



    @ExceptionHandler(FeignException.BadRequest.class)
    public Map<String, Object> handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return new JSONObject(e.contentUTF8()).toMap();
    }

    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public String handleFeignException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        log.error(e.getMessage());
     //   System.out.println(response);

        return null;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionResponse handleException(final Exception exception,
                                      final HttpServletRequest request) {

        if(exception instanceof FeignException){
            exception.printStackTrace();
            log.info(exception.getMessage());
            return null;
        }

        log.info(exception.getMessage());
        log.error(exception.getMessage());
        exception.printStackTrace();
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        error.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setResponseTime(LocalDateTime.now());

        return error;
    }


}
