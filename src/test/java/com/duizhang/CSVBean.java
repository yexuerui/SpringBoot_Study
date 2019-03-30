package com.duizhang;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;

//id,name,data
public class CSVBean {

    @CsvBindByName(column = "jrn_no")
    private Integer id;

    @CsvBindByName
    @CsvDate("yyyy-MM-dd")
    private Date Data;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "age")
    private Integer age;

    @CsvBindByName(column = "sex")
    private String gender;

    @CsvBindByName(column = "学历")
    private String xueli;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getData() {
        return Data;
    }

    public void setData(Date data) {
        Data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getXueli() {
        return xueli;
    }

    public void setXueli(String xueli) {
        this.xueli = xueli;
    }

    @Override
    public String toString() {
        return "CSVBean{" +
                "id=" + id +
                ", Data=" + Data +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", xueli='" + xueli + '\'' +
                '}';
    }
}
