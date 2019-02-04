package com.ats.shivshambhoo.model;

public class PaymentTerm {

    private int payTermId;
    private String payTerm;
    private String date;
    private int isUsed;
    private int delStatus;


    public int getPayTermId() {
        return payTermId;
    }

    public void setPayTermId(int payTermId) {
        this.payTermId = payTermId;
    }

    public String getPayTerm() {
        return payTerm;
    }

    public void setPayTerm(String payTerm) {
        this.payTerm = payTerm;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    public int getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(int delStatus) {
        this.delStatus = delStatus;
    }

    @Override
    public String toString() {
        return "PaymentTerm{" +
                "payTermId=" + payTermId +
                ", payTerm='" + payTerm + '\'' +
                ", date='" + date + '\'' +
                ", isUsed=" + isUsed +
                ", delStatus=" + delStatus +
                '}';
    }
}
