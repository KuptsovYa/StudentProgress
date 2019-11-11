package com.student.progress.exc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Failed to create new user")
public class FailAddingUserException extends Exception{

    public FailAddingUserException(){
        super("Fail to create new user, try again later");
    }
}
