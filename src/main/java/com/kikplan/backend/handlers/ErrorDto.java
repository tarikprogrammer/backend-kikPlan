package com.kikplan.backend.handlers;


import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class ErrorDto {
    private String errorMsg;
    private Set<String> violations;
    private String errorSource;
}
