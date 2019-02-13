package com.yxr.enums;

public enum Gender {

    nan(0,"男"),nv(1,"女");
    private int code;
    private String sex;

    private Gender(int code, String sex) {
        this.code = code;
        this.sex = sex;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "code=" + code +
                ", sex='" + sex + '\'' +
                '}';
    }
}
