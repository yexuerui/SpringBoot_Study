package com.test;

public class TestThreadLocal {

    private static final ThreadLocal<String> encode = new ThreadLocal<String>();

    private static final ThreadLocal<String> requestIdThreadLocal = new ThreadLocal<String>();

    private String _encode = new String();

    public String getRequestId() {
        return requestIdThreadLocal.get();
    }

    public void setRequestId(String requestId) {
        requestIdThreadLocal.set(requestId);
    }

    public void setOne(String str) {
        encode.set(str);
    }

    public String getOne() {
        return encode.get();
    }

    public String get_encode() {
        return _encode;
    }

    public void set_encode(String _encode) {
        this._encode = _encode;
    }

    public void remove() {
        encode.remove();
    }

}
