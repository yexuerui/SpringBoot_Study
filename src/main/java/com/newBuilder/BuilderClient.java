package com.newBuilder;

//客户端生成对象
public class BuilderClient {

    public static void main(String[] args) {
        //创建构造器对象
        InsuranceContract.ContractBuilder builder =
                new InsuranceContract.ContractBuilder("XP20180324", 20180322, 20190330);
        //设置需要的数据
        InsuranceContract contract = builder.setPersonName("小胖").setOtherData("扩展数据").build();
        System.out.println(contract);
    }
}
