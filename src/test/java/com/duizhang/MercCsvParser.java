package com.duizhang;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.List;

public class MercCsvParser {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
//        String path = "C:\\Users\\admin\\Desktop\\fff.csv";
        String path = "C:\\Users\\admin\\Desktop\\301101810018847203611.csv";
        String charset = "utf-8";
        FileInputStream fileInputStream = new FileInputStream(path);
        Reader reader = new InputStreamReader(fileInputStream, charset);
        List<MercBean> beans = new CsvToBeanBuilder<MercBean>(reader).
                withType(MercBean.class).build().parse();
        System.out.println(beans);
    }
}
