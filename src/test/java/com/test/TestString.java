package com.test;

import com.rabbitmq.client.Address;
import org.apache.commons.lang3.StringUtils;

public class TestString {
    public static void setAddrs(String paramString) {
        Address[] addrs;
        String[] arrayOfString1 = StringUtils.split(paramString, ";");
        addrs = new Address[arrayOfString1.length];
        for (int i = 0; i < arrayOfString1.length; ++i) {
            String[] arrayOfString2 = StringUtils.split(arrayOfString1[i], ":");
            addrs[i] = new Address(arrayOfString2[0], Integer.parseInt(arrayOfString2[1]));
        }
    }

    public static void main(String[] args) {
        String address = "192.168.8.66：5672";
        setAddrs(address);

    }
}
