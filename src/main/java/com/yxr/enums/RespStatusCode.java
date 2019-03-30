package com.yxr.enums;

public enum RespStatusCode {

    FAILED(401, "用户信息不存在"),
    AUTH_ERROR(401, "认证失败"),
    SUCCESS(400, "成功");
    //私有的参数
    private int status;
    private String message;

    //私有的构造方法
    private RespStatusCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }


    public String getMessage() {
        return message;
    }

}
