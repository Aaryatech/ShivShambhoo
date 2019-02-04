package com.ats.shivshambhoo.model;

public class PoDetail {

    private int poDetailId;
    private int poId;
    private int itemId;
    private float poRate;
    private float poQty;
    private float poConsumeQty;
    private float poRemainingQty;
    private int status;
    private String remark;
    private float taxAmt;
    private float taxPer;
    private float taxableAmt;
    private float otherCharges;
    private float total;
    private int quDetailId;
    private String varchar1;
    private String varchar2;
    private int extra1;
    private int extra2;

    public int getPoDetailId() {
        return poDetailId;
    }

    public void setPoDetailId(int poDetailId) {
        this.poDetailId = poDetailId;
    }

    public int getPoId() {
        return poId;
    }

    public void setPoId(int poId) {
        this.poId = poId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getPoRate() {
        return poRate;
    }

    public void setPoRate(float poRate) {
        this.poRate = poRate;
    }

    public float getPoQty() {
        return poQty;
    }

    public void setPoQty(float poQty) {
        this.poQty = poQty;
    }

    public float getPoConsumeQty() {
        return poConsumeQty;
    }

    public void setPoConsumeQty(float poConsumeQty) {
        this.poConsumeQty = poConsumeQty;
    }

    public float getPoRemainingQty() {
        return poRemainingQty;
    }

    public void setPoRemainingQty(float poRemainingQty) {
        this.poRemainingQty = poRemainingQty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public float getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(float taxAmt) {
        this.taxAmt = taxAmt;
    }

    public float getTaxPer() {
        return taxPer;
    }

    public void setTaxPer(float taxPer) {
        this.taxPer = taxPer;
    }

    public float getTaxableAmt() {
        return taxableAmt;
    }

    public void setTaxableAmt(float taxableAmt) {
        this.taxableAmt = taxableAmt;
    }

    public float getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(float otherCharges) {
        this.otherCharges = otherCharges;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getQuDetailId() {
        return quDetailId;
    }

    public void setQuDetailId(int quDetailId) {
        this.quDetailId = quDetailId;
    }

    public String getVarchar1() {
        return varchar1;
    }

    public void setVarchar1(String varchar1) {
        this.varchar1 = varchar1;
    }

    public String getVarchar2() {
        return varchar2;
    }

    public void setVarchar2(String varchar2) {
        this.varchar2 = varchar2;
    }

    public int getExtra1() {
        return extra1;
    }

    public void setExtra1(int extra1) {
        this.extra1 = extra1;
    }

    public int getExtra2() {
        return extra2;
    }

    public void setExtra2(int extra2) {
        this.extra2 = extra2;
    }

    @Override
    public String toString() {
        return "PoDetail{" +
                "poDetailId=" + poDetailId +
                ", poId=" + poId +
                ", itemId=" + itemId +
                ", poRate=" + poRate +
                ", poQty=" + poQty +
                ", poConsumeQty=" + poConsumeQty +
                ", poRemainingQty=" + poRemainingQty +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", taxAmt=" + taxAmt +
                ", taxPer=" + taxPer +
                ", taxableAmt=" + taxableAmt +
                ", otherCharges=" + otherCharges +
                ", total=" + total +
                ", quDetailId=" + quDetailId +
                ", varchar1='" + varchar1 + '\'' +
                ", varchar2='" + varchar2 + '\'' +
                ", extra1=" + extra1 +
                ", extra2=" + extra2 +
                '}';
    }
}
