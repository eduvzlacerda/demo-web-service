package com.example.demowebservice.ExceptionHandling;

public class UserServiceException extends RuntimeException{

    public UserServiceException(String message){
        super(message);
    }

}
