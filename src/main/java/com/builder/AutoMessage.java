package com.builder;


import java.time.LocalDate;

public abstract class AutoMessage {
    //收件地址
    private String toAddress;
    //发件地址
    private String fromAddress;
    //标题
    private String subject;
    //内容
    private String body;
    //发送日期
    private LocalDate sendData;

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getSendData() {
        return sendData;
    }

    public void setSendData(LocalDate sendData) {
        this.sendData = sendData;
    }

}
