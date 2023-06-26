package com.user.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException{

//    String resourceName;
//    String fieldName;
//
//    long fieldValue;
//
//    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
//        super(String.format("%s not fount %s : %s",resourceName,fieldName,fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
//    }

    public ResourceNotFoundException()
    {
        super("Resources  not found in server !!!");

    }

    public  ResourceNotFoundException(String message)
    {
        super(message);
    }
}
