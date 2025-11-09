package com.student.management.exception;

import com.student.management.structure.ResponseStructure;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseStructure<Map<String,String>>> handleConstraintViolationException(ConstraintViolationException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(e -> errors.put(e.getPropertyPath().toString()
        ,e.getMessage()));
        ResponseStructure<Map<String,String>> structure = new ResponseStructure<>();
        structure.setMessage("Validation failed");
        structure.setData(errors);
        structure.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(structure,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error
        -> errors.put(error.getField(),error.getDefaultMessage()));

        ResponseStructure<Map<String,String>> structure = new ResponseStructure<>();
        structure.setMessage("Validation failed");
        structure.setData(errors);
        structure.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(structure,HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException ex){
        ResponseStructure<String> structure = new ResponseStructure<>();
        structure.setMessage("Operation unsuccessful");
        structure.setData(ex.getMessage());
        structure.setHttpStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleNoDataFoundException(NoDataFoundException ex){
        ResponseStructure<String> structure = new ResponseStructure<>();
        structure.setMessage("Operation unsuccessful");
        structure.setData(ex.getMessage());
        structure.setHttpStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);
    }
}
