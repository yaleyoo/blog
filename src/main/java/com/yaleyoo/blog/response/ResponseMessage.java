package com.yaleyoo.blog.response;

import java.io.Serializable;

/**
 * Created by steve on 18/3/19.
 */

/**
 * Return Message will be in format:
 * {
 *      returnCode : "0" - success OR "1" - failure,
 *      returnMessage: "success" OR "failure" OR customised String,
 *      data: JSON string
 * }
 */
public class ResponseMessage implements Serializable{


    private static final long serialVersionUID = 1L;

    /** status 0：success  1：failure */
    private String returnCode;

    /** response message */
    private String returnMessage;

    public ResponseMessage(){

    }

    public ResponseMessage(boolean success, String returnMessage) {
        this.returnCode = success ? "0" : "1";
        this.returnMessage = returnMessage;
    }


    public ResponseMessage(boolean success) {
        if (success) {
            this.returnCode = "0";
            this.returnMessage = "success";
        } else {
            this.returnCode = "1";
            this.returnMessage = "failure";
        }
    }

    public void setSuccess(boolean success) {
        if (success) {
            this.returnCode = "0";
            this.returnMessage = "success";
        } else {
            this.returnCode = "1";
            this.returnMessage = "failure";
        }
    }

    public void setSuccess(boolean success, String returnMessage) {
        this.returnCode = success ? "0" : "1";
        this.returnMessage = returnMessage;
    }

    /** Determine the result */
    public boolean success() {
        return "0".equals(this.returnCode);
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("returnCode: ").append(this.returnCode).append(';');
        sb.append("returnMessage: ").append(this.returnMessage).append(';');
        return sb.toString();
    }
}
