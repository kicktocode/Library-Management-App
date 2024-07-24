package com.example.library_app.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> details = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(exception.getContentType());
        builder.append("Media type not supported .Supported media types are :");
        exception.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        details.add(builder.toString());

        Map<String, Object> errors = new HashMap<>();
        errors.put("error Message", details);
        errors.put("error code", status.value());
        errors.put("httpStatus", status);

        return ResponseEntity.status(status).body(errors);

    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception,
            HttpStatus status,
            HttpHeaders headers,
            WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("error Message", exception.getMessage());
        errors.put("Http Status", status);
        errors.put("Error code ", status.value());
        return ResponseEntity.status(status).body(errors);

    }

    protected ResponseEntity<Object> handleMissingServletRequestParam
            (MissingServletRequestParameterException exception,
             HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("Http Status", status);
        errors.put("Http error code", status.value());
        errors.put("Error ", exception.getParameterName() +
                " parameter is missing");
        return ResponseEntity.status(status).body(errors);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
       exception.getBindingResult().getFieldErrors().forEach(
           err->errors.put(err.getField(),err.getDefaultMessage())
       );
       return  ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<?> handleException(GenericException exception)
    {
        Map<String,Object> errors = new HashMap<>();
        errors.put("error",exception.getErrorMessage());
        errors.put("error Code",exception.getErrorCode());
        return ResponseEntity
                .status(exception.getHttpStatus()!=null? exception.getHttpStatus()
                        :HttpStatus.BAD_REQUEST)
                .body(errors);

    }
}