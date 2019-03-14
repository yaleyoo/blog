package com.yaleyoo.blog.exception;

/**
 * Created by steve on 14/3/19.
 */

public class ErrorResponse {
    private String errMessage;
    private String errDetails;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, String details) {
        this.errMessage = message;
        this.errDetails = details;
    }

    public String getMessage() {
        return errMessage;
    }

    public void setMessage(String message) {
        this.errMessage = message;
    }

    public String getDetails() {
        return errDetails;
    }

    public void setDetails(String details) {
        this.errDetails = details;
    }
}
