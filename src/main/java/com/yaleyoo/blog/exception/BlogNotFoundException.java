package com.yaleyoo.blog.exception;

/**
 * Created by steve on 14/3/19.
 */
public class BlogNotFoundException extends Exception{
    public BlogNotFoundException(){
        super("Cause: The blog looked for is not exist.");
    }
}
