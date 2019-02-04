package com.ats.shivshambhoo.model;

public class CustomerType {

    private int custTypeId;
    private String custTypeName;
    private int delStatus;

    public int getCustTypeId() {
        return custTypeId;
    }

    public void setCustTypeId(int custTypeId) {
        this.custTypeId = custTypeId;
    }

    public String getCustTypeName() {
        return custTypeName;
    }

    public void setCustTypeName(String custTypeName) {
        this.custTypeName = custTypeName;
    }

    public int getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(int delStatus) {
        this.delStatus = delStatus;
    }

    @Override
    public String toString() {
        return "CustomerType{" +
                "custTypeId=" + custTypeId +
                ", custTypeName='" + custTypeName + '\'' +
                ", delStatus=" + delStatus +
                '}';
    }
}
