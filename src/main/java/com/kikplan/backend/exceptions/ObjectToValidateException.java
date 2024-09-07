package com.kikplan.backend.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class ObjectToValidateException extends RuntimeException {

    private final Set<String> violations;
    private final String violationSource;
}
