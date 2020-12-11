package com.suman.basicAppWithRolePriviledge.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice
@Priority(1)
@Slf4j
public class ExceptionHandlerControllerAdvice {


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse handleResourceNotFound(final ResourceNotFoundException exception,
                                             final HttpServletRequest request) {

        log.error(exception.getMessage());
        exception.printStackTrace();
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        error.setHttpStatus(HttpStatus.NOT_FOUND);
        error.setResponseTime(LocalDateTime.now());

        return error;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public @ResponseBody
    ExceptionResponse handleAuthorizationException(AccessDeniedException ex, HttpServletRequest req) {
        // build a response body out of ex, and return that
        log.error(ex.getMessage());
        ex.printStackTrace();
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(req.getRequestURI());
        error.setHttpStatus(HttpStatus.FORBIDDEN);
        error.setResponseTime(LocalDateTime.now());

        return error;
    }

    //JwtException
    @ExceptionHandler(JwtException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public @ResponseBody
    ExceptionResponse handleJwtException(JwtException ex, HttpServletRequest req) {
        ExceptionResponse error = new ExceptionResponse();
        log.info(ex.getMessage());
        ex.printStackTrace();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(req.getRequestURI());
        error.setHttpStatus(HttpStatus.UNAUTHORIZED);
        error.setResponseTime(LocalDateTime.now());

        return error;
    }

    //NullPointerException
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public @ResponseBody
    ExceptionResponse handleNullPointerException(NullPointerException ex, HttpServletRequest req) {
        log.info(ex.getMessage());
        ex.printStackTrace();
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(req.getRequestURI());
        error.setHttpStatus(HttpStatus.NOT_IMPLEMENTED);
        error.setResponseTime(LocalDateTime.now());

        return error;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError handleMethodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException ex) {

        log.debug("ExceptionHandler>>>>>>>>>>>>>>>>>>>>>>>>MethodArgumentNotValidException");
        log.info(ex.getMessage());
        ex.printStackTrace();
        ApiError apiError = new ApiError();
        List<String> errorList = new ArrayList<>();

        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError error : allErrors) {
            errorList.add(error.getDefaultMessage());
        }
        apiError.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiError.setResponseTime(LocalDateTime.now());
        apiError.setErrors(errorList);

        return apiError;
    }


    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleMethodJsonParseException(HttpServletRequest req, JsonParseException ex) {

        log.info("ExceptionHandler>>>>>>>>>>>>>>>>>>>>>>>>JsonParseException");
        log.info(ex.getMessage());
        ex.printStackTrace();
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(req.getRequestURI());
        error.setHttpStatus(HttpStatus.BAD_REQUEST);
        error.setResponseTime(LocalDateTime.now());

        return error;
    }

//BadCredentialsException

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ExceptionResponse handleMethodBadCredentialsException(HttpServletRequest req, BadCredentialsException ex) {

        log.info("ExceptionHandler>>>>>>>>>>>>>>>>>>>>>>>>BadCredentialsException");
        log.info(ex.getMessage());
        ex.printStackTrace();
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(req.getRequestURI());
        error.setHttpStatus(HttpStatus.UNAUTHORIZED);
        error.setResponseTime(LocalDateTime.now());

        return error;
    }


    @ExceptionHandler(MyFileNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionResponse MyFileNotFoundExceptionException(HttpServletRequest req, MyFileNotFoundException ex) {

        log.info("ExceptionHandler>>>>>>>>>>>>>>>>>>>>>>>>MyFileNotFoundException");
        log.info("Error message ={} and request header ={} ",ex.getMessage(),req.getRequestURI());
     //   ex.printStackTrace();
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(req.getRequestURI());
        error.setHttpStatus(HttpStatus.NOT_FOUND);
        error.setResponseTime(LocalDateTime.now());

        return error;
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public ExceptionResponse ForbiddenExceptionHandler(HttpServletRequest req, ForbiddenException ex) {

        log.info("ExceptionHandler>>>>>>>>>>>>>>>>>>>>>>>>ForbiddenException");
        log.info("Error message ={} and request ={} ",ex.getMessage(),req.getRequestURI());
      //  ex.printStackTrace();
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(req.getRequestURI());
        error.setHttpStatus(HttpStatus.FORBIDDEN);
        error.setResponseTime(LocalDateTime.now());

        return error;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handlerBadCredentialsException(HttpServletRequest req, BadRequestException ex) {

        log.info("ExceptionHandler>>>>>>>>>>>>>>>>>>>>>>>>BadRequestException");
        log.info(ex.getMessage());
        ex.printStackTrace();
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(req.getRequestURI());
        error.setHttpStatus(HttpStatus.BAD_REQUEST);
        error.setResponseTime(LocalDateTime.now());

        return error;
    }


}
