package com.miniproject.usermanagement.exception;

public class MultipleUsernameException extends RuntimeException {

    public MultipleUsernameException(String message){
        super(message);
    }    

    public MultipleUsernameException(String message, Throwable cause){
        super(message, cause);
    }

}
