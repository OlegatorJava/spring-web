package com.geekbrains.spring.web.exceptions;

import java.util.List;

public class FieldValidationError {
    private List<String> errorFieldMessages;

    public FieldValidationError(List<String> errorFieldMessages){
        this.errorFieldMessages = errorFieldMessages;
    }
}
