package com.example.for_testdemo1.Common;

public class BusinessException extends RuntimeException{
    private Integer code;
    public BusinessException(Integer code,String message) {
        super(message);
        this.code = code;
    }
    public BusinessException(String message) {
        this(500,message);
    }
    public Integer getCode(){
        return code;
    }


}
