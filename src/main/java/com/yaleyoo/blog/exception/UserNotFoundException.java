package com.yaleyoo.blog.exception;

/**
 * Created by steve on 14/3/19.
 */
public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("Cause: The user looked for is not exist.");
    }
}
