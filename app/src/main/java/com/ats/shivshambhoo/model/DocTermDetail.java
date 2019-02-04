package com.ats.shivshambhoo.model;

public class DocTermDetail {

    private int termDetailId;
    private int termId;

    private String termDesc;

    private int delStatus;
    private int sortNo;

    public int getTermDetailId() {
        return termDetailId;
    }

    public void setTermDetailId(int termDetailId) {
        this.termDetailId = termDetailId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermDesc() {
        return termDesc;
    }

    public void setTermDesc(String termDesc) {
        this.termDesc = termDesc;
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

    @Override
    public String toString() {
        return "DocTermDetail{" +
                "termDetailId=" + termDetailId +
                ", termId=" + termId +
                ", termDesc='" + termDesc + '\'' +
                ", delStatus=" + delStatus +
                ", sortNo=" + sortNo +
                '}';
    }
}
