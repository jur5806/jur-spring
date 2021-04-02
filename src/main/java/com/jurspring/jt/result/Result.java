package com.jurspring.jt.result;

import lombok.Data;

@Data
public class Result {
    //响应器
    private int code;
    private String message;
    private Object result;

    Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.result = data;
    }
}
