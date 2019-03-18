package com.yaleyoo.blog.response;

import java.io.Serializable;

/**
 * Created by steve on 18/3/19.
 */
public class AbstractHttpResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ResponseMessage response;

    protected T data;

    public ResponseMessage getResponse() {
        return response;
    }

    public void setResponse(ResponseMessage error) {
        this.response = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
