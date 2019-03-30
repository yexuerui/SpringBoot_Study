package com.yxr.enums;

public class RedisSessionResp {

    //错误码
    private int status;

    //错误信息
    private String message;

    //响应数据
    private String data;


    public static RedisSessionResp build(int status, String message, String data) {
        return new RedisSessionResp(status, message, data);
    }

    public static RedisSessionResp build(int status, String message) {
        return new RedisSessionResp(status, message, null);
    }

    public RedisSessionResp() {
    }

    public RedisSessionResp(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public RedisSessionResp(int status, String message, String data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
