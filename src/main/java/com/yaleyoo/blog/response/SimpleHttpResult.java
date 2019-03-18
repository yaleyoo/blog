package com.yaleyoo.blog.response;

/**
 * Created by steve on 18/3/19.
 */
public class SimpleHttpResult<T> extends AbstractHttpResult<T> {


    /** Default result is success **/
    public SimpleHttpResult() {
        this.response = new ResponseMessage(true);
    }
    /** Specify response data, when success **/
    public SimpleHttpResult(T t) {
        this.response = new ResponseMessage(true);
        this.data  = t;
    }
    /** Specify result and return message **/
    public SimpleHttpResult(boolean success, String returnMessage) {
        this.response = new ResponseMessage(success, returnMessage);
    }
    /** Specify result with default return message - 'success'/'failure' **/
    public SimpleHttpResult(boolean success) {
        this.response = new ResponseMessage(success);
    }
    /** Specify result with return Message and data **/
    public SimpleHttpResult(boolean success, String returnMessage, T data){
        this.response = new ResponseMessage(success, returnMessage);
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.response.setSuccess(success);
    }

    public void setSuccess(boolean success, String returnMessage) {
        this.response.setSuccess(success, returnMessage);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("msg[");
        sb.append(this.response.toString());
        sb.append(']');
        sb.append("data[");
        if (data != null) {
            sb.append(this.data.toString());
        }else{
            sb.append("null");
        }
        sb.append(']');
        return sb.toString();
    }
}
