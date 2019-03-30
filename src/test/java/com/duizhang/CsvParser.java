package com.duizhang;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.io.input.BOMInputStream;

import java.io.*;
import java.util.List;

public class CsvParser {

    public static void main(String[] args) throws FileNotFoundException {
//        String path = "C:\\Users\\admin\\Desktop\\fff.csv";
        String path = "C:\\Users\\admin\\Desktop\\ooo.csv";
        Reader reader = new InputStreamReader(new BOMInputStream(new FileInputStream(path)));
//        List<CSVBean> beans = new CsvToBeanBuilder<CSVBean>(reader).withType(CSVBean.class).build().parse();

        //对象A
        CsvToBean<CSVBean> build = new CsvToBeanBuilder<CSVBean>(reader).withType(CSVBean.class).build();
        MyCsvToBean<CSVBean> csvBeanMyCsvToBean = new MyCsvToBean<CSVBean>(build);
        System.out.println("---->" + csvBeanMyCsvToBean.parse());


//        CsvToBeanBuilder<CSVBean> csvBeanCsvToBeanBuilder = new CsvToBeanBuilder<>(reader);
//        System.out.println("111 : "+csvBeanCsvToBeanBuilder);
//        MyCsvToBeanBuilder<CSVBean> csvBeanMyCsvToBeanBuilder = new MyCsvToBeanBuilder<CSVBean>(reader,csvBeanCsvToBeanBuilder);
////        CsvToBean<CSVBean> csvBeanCsvToBean =csvBeanMyCsvToBeanBuilder.withType(CSVBean.class).build();
//        List<CSVBean> csvBeans = csvBeanMyCsvToBeanBuilder.withType(CSVBean.class).build().parse();
//        System.out.println(csvBeans);

//        MyCsvToBean<CSVBean> csvBeanMyCsvToBean = new MyCsvToBean<CSVBean>(csvBeanCsvToBean);
//        List<CSVBean> beans1 = csvBeanMyCsvToBean.parse();
//        System.out.println(beans1);
    }
}
