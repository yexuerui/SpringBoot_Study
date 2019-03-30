package com.JsonSerializer;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class User implements Serializable {


    private static final long serialVersionUID = 4731277808546534921L;
    public static String STATE_STR = "new";

    private String name;
    private List<String> sports;
    //    //序列化格式
    @JsonSerialize(using = LocalDateSerializer.class)
//    //反序列化格式
    @JsonDeserialize(using = LocalDateDeserializer.class)
//    //Json格式
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSports() {
        return sports;
    }

    public void setSports(List<String> sports) {
        this.sports = sports;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sports=" + sports +
                ", date=" + date +
                '}';
    }
}
