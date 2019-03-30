package com.duizhang;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanFilter;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.concurrent.AccumulateCsvResults;
import com.opencsv.bean.concurrent.IntolerantThreadPoolExecutor;
import com.opencsv.bean.concurrent.OrderedObject;
import com.opencsv.bean.concurrent.ProcessCsvLine;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class MyCsvToBean<T> extends CsvToBean<T> {

    private CsvToBean<T> csvToBean;



    public MyCsvToBean(CsvToBean<T> csvToBean) {
        this.csvToBean = csvToBean;

    }

    @Override
    public List<T> parse() throws IllegalStateException {
        return super.parse();
    }

}
