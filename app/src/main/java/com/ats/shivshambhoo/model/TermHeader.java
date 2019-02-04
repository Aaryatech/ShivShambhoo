package com.ats.shivshambhoo.model;

import java.util.List;

public class TermHeader {

    private int termId;

    private String termTitle;
    private int docId;
    private int delStatus;
    private int sortNo;
    private int exInt1;
    private int exInt2;
    private String exVar1;
    private String exVar2;
    List<DocTermDetail> detailList;

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(int delStatus) {
        this.delStatus = delStatus;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
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

    public List<DocTermDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DocTermDetail> detailList) {
        this.detailList = detailList;
    }

    @Override
    public String toString() {
        return "TermHeader{" +
                "termId=" + termId +
                ", termTitle='" + termTitle + '\'' +
                ", docId=" + docId +
                ", delStatus=" + delStatus +
                ", sortNo=" + sortNo +
                ", exInt1=" + exInt1 +
                ", exInt2=" + exInt2 +
                ", exVar1='" + exVar1 + '\'' +
                ", exVar2='" + exVar2 + '\'' +
                ", detailList=" + detailList +
                '}';
    }
}
