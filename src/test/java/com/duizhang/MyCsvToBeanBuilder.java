package com.duizhang;

import com.opencsv.CSVParser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.opencsvUtils;

import java.io.Reader;
import java.util.ResourceBundle;

public class MyCsvToBeanBuilder<T>  extends CsvToBeanBuilder<T> {

    private CsvToBeanBuilder<T> csvToBeanBuilder;
    private Reader reader;

    public MyCsvToBeanBuilder(Reader reader) {
        super(reader);
    }

    public MyCsvToBeanBuilder(Reader reader, CsvToBeanBuilder<T> csvToBeanBuilder) {
        super(reader);
        this.csvToBeanBuilder = csvToBeanBuilder;
        System.out.println("csvToBeanBuilder : "+csvToBeanBuilder);
    }

    public CsvToBeanBuilder<T> withType(Class<? extends T> type) {
        return csvToBeanBuilder.withType(type);
    }
    public CsvToBean<T> build() throws IllegalStateException {
        return csvToBeanBuilder.build();
    }
}
