package com.yaleyoo.blog.exception;

/**
 * Created by steve on 14/3/19.
 */
public class BlogNotFoundException extends Exception{
    public BlogNotFoundException(String blogName){
        super("Cause: The blog "+ blogName +" looked for is not exist.");
    }
}
