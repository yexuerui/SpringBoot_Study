package com.newBuilder;

//保险者类
public class InsuranceContract {

    //保险合同编号
    private String contractId;
    //个人
    private String personName;
    //公司
    private String companyName;
    //保险生效日期
    private long begintDate;
    //保险失效日期
    private long endDate;
    //扩展数据
    private String otherData;


    //私有构造方法(导演者角色)
    private InsuranceContract(ContractBuilder builder) {
        this.contractId = builder.contractId;
        this.personName = builder.personName;
        this.companyName = builder.companyName;
        this.begintDate = builder.beginDate;
        this.endDate = builder.endDate;
        this.otherData = builder.otherData;
    }

    //具体的建造者合并到了产品对象中了，这个静态内部类之为外部类提供服务
    public static class ContractBuilder {
        private String contractId;
        private String personName;
        private String companyName;
        private long beginDate;
        private long endDate;
        private String otherData;

        public ContractBuilder(String contractId, long beginDate, long endDate) {
            this.contractId = contractId;
            this.beginDate = beginDate;
            this.endDate = endDate;
        }


        public ContractBuilder setPersonName(String personName) {
            this.personName = personName;
            return this;
        }

        public ContractBuilder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public ContractBuilder setOtherData(String otherData) {
            this.otherData = otherData;
            return this;
        }

        //建造方法，构造对象并返回（在建造方法中进行数据校验）
        public InsuranceContract build() {
            if (contractId == null || contractId.trim().length() == 0) {
                throw new IllegalArgumentException("contractId is null");
            }
            //判断个人或者公司是否为null
            boolean signPerson = personName != null && personName.trim().length() > 0;
            boolean signCompany = companyName != null && companyName.trim().length() > 0;
            if (signPerson && personName == null) {
                throw new IllegalArgumentException("companyName and personName 不能同时存在");
            }
            if (beginDate <= 0) {
                throw new IllegalArgumentException("beginDate 不合法");
            }
            if (endDate <= 0) {
                throw new IllegalArgumentException("endDate 不合法");
            }
            if (beginDate > endDate) {
                throw new IllegalArgumentException("beginDate大于endDate");
            }
            return new InsuranceContract(this);
        }
    }
}
