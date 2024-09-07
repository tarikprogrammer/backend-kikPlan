package com.kikplan.backend.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OperationNotPermittedException extends RuntimeException{
    private final String msgError;
    private final String source;
}
