package com.elitehomes.core.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException{

    private String objectName;

    public NotFoundException(String objectName, String message) {
        super(message);
        this.objectName = objectName;
    }
}
