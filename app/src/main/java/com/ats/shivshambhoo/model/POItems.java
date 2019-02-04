package com.ats.shivshambhoo.model;

public class POItems {

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
    private String poNo;
    private String poDate;
    private String itemCode;
    private String itemName;
    private String shortName;
    private float dispatchLimit;
    private float royaltyRate;
    private float freightRate;
    private float itemRate1;
    private String uomName;
    private float qty;

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

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getPoDate() {
        return poDate;
    }

    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public float getDispatchLimit() {
        return dispatchLimit;
    }

    public void setDispatchLimit(float dispatchLimit) {
        this.dispatchLimit = dispatchLimit;
    }

    public float getRoyaltyRate() {
        return royaltyRate;
    }

    public void setRoyaltyRate(float royaltyRate) {
        this.royaltyRate = royaltyRate;
    }

    public float getFreightRate() {
        return freightRate;
    }

    public void setFreightRate(float freightRate) {
        this.freightRate = freightRate;
    }

    public float getItemRate1() {
        return itemRate1;
    }

    public void setItemRate1(float itemRate1) {
        this.itemRate1 = itemRate1;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "POItems{" +
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
                ", poNo='" + poNo + '\'' +
                ", poDate='" + poDate + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", dispatchLimit=" + dispatchLimit +
                ", royaltyRate=" + royaltyRate +
                ", freightRate=" + freightRate +
                ", itemRate1=" + itemRate1 +
                ", uomName='" + uomName + '\'' +
                ", qty=" + qty +
                '}';
    }
}
