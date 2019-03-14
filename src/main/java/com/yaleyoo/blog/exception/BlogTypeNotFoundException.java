package com.yaleyoo.blog.exception;

/**
 * Created by steve on 14/3/19.
 */
public class BlogTypeNotFoundException extends Exception{
    public BlogTypeNotFoundException(){
        super("Cause: The blog type looked for is not exist.");
    }
}
