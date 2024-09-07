package com.kikplan.backend.handlers;


import com.kikplan.backend.exceptions.ObjectToValidateException;
import com.kikplan.backend.exceptions.OperationNotPermittedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

@RestControllerAdvice
public class HandlersException {

    @ExceptionHandler(ObjectToValidateException.class)
    public ResponseEntity<ErrorDto> objectToValidateException(ObjectToValidateException e){
        ErrorDto errorDto=ErrorDto.builder()
                .errorMsg("Object was not validated")
                .errorSource(e.getViolationSource())
                .violations(e.getViolations())
                .build();


        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorDto);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto>entityNotFoundException(EntityNotFoundException e){
        ErrorDto errorDto=ErrorDto.builder()

                .errorMsg(e.getMessage())
                .errorSource(e.getClass().getName())
                .violations(Set.of("Entity not Found "))
                .build();


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ErrorDto>operationNotPermittedException(OperationNotPermittedException e){
        ErrorDto errorDto=ErrorDto.builder()
                .errorSource(e.getClass().getName())
                .errorMsg(e.getMsgError())
                .violations(Set.of(e.getMsgError()))
                .build();



        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorDto);
    }
}
