package com.ats.shivshambhoo.model;

public class Document {

    private int docId;
    private String docName;
    private String docDescription;
    private String docPrefix;
    private int delStatus;
    private String fromDate;
    private String toDate;
    private int docCode;
    private int srNo;
    private int exInt1;
    private int exInt2;
    private String exVar1;
    private String exVar2;
    private String exDate1;
    private String exDate2;
    private int exBool1;
    private int exBool2;

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocDescription() {
        return docDescription;
    }

    public void setDocDescription(String docDescription) {
        this.docDescription = docDescription;
    }

    public String getDocPrefix() {
        return docPrefix;
    }

    public void setDocPrefix(String docPrefix) {
        this.docPrefix = docPrefix;
    }

    public int getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(int delStatus) {
        this.delStatus = delStatus;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getDocCode() {
        return docCode;
    }

    public void setDocCode(int docCode) {
        this.docCode = docCode;
    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public int getExInt1() {
        return exInt1;
    }

    public void setExInt1(int exInt1) {
        this.exInt1 = exInt1;
    }

    public int getExInt2() {
        return exInt2;
    }

    public void setExInt2(int exInt2) {
        this.exInt2 = exInt2;
    }

    public String getExVar1() {
        return exVar1;
    }

    public void setExVar1(String exVar1) {
        this.exVar1 = exVar1;
    }

    public String getExVar2() {
        return exVar2;
    }

    public void setExVar2(String exVar2) {
        this.exVar2 = exVar2;
    }

    public String getExDate1() {
        return exDate1;
    }

    public void setExDate1(String exDate1) {
        this.exDate1 = exDate1;
    }

    public String getExDate2() {
        return exDate2;
    }

    public void setExDate2(String exDate2) {
        this.exDate2 = exDate2;
    }

    public int getExBool1() {
        return exBool1;
    }

    public void setExBool1(int exBool1) {
        this.exBool1 = exBool1;
    }

    public int getExBool2() {
        return exBool2;
    }

    public void setExBool2(int exBool2) {
        this.exBool2 = exBool2;
    }

    @Override
    public String toString() {
        return "Document{" +
                "docId=" + docId +
                ", docName='" + docName + '\'' +
                ", docDescription='" + docDescription + '\'' +
                ", docPrefix='" + docPrefix + '\'' +
                ", delStatus=" + delStatus +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", docCode=" + docCode +
                ", srNo=" + srNo +
                ", exInt1=" + exInt1 +
                ", exInt2=" + exInt2 +
                ", exVar1='" + exVar1 + '\'' +
                ", exVar2='" + exVar2 + '\'' +
                ", exDate1='" + exDate1 + '\'' +
                ", exDate2='" + exDate2 + '\'' +
                ", exBool1=" + exBool1 +
                ", exBool2=" + exBool2 +
                '}';
    }
}
