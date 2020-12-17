package com.jurspring.jt.result;

public class Result {
    //响应器
    private  int code;

    public Result(int code) {
        this.code = code;

    }

    public int getCode() {
        return  code;
    }

    public  void  setCode(int code) {
        this.code = code;
    }
}
