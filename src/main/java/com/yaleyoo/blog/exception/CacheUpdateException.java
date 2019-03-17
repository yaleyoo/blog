package com.yaleyoo.blog.exception;

/**
 * Created by steve on 16/3/19.
 */
public class CacheUpdateException extends Exception{
    public CacheUpdateException(){
        super("Cause: Failed to update cache data. Blog update failed. Please try again later or connect the service provider.");
    }
}
