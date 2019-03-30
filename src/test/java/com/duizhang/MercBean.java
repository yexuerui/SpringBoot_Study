package com.duizhang;

import com.opencsv.bean.CsvBindByName;

public class MercBean {

    @CsvBindByName(column = "支付订单流水")
    private String jrnNO;

    @CsvBindByName(column = "支付工具")
    private String payMed;

    @CsvBindByName(column = "订单金额")
    private String txAmt;

    public String getJrnNO() {
        return jrnNO;
    }

    public void setJrnNO(String jrnNO) {
        this.jrnNO = jrnNO;
    }

    public String getPayMed() {
        return payMed;
    }

    public void setPayMed(String payMed) {
        this.payMed = payMed;
    }

    public String getTxAmt() {
        return txAmt;
    }

    public void setTxAmt(String txAmt) {
        this.txAmt = txAmt;
    }

    @Override
    public String toString() {
        return "MercBean{" +
                "jrnNO='" + jrnNO + '\'' +
                ", payMed='" + payMed + '\'' +
                ", txAmt='" + txAmt + '\'' +
                '}';
    }
}
