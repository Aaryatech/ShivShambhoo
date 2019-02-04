package com.ats.shivshambhoo.model;

public class QuotDetail {

    private int quotDetailId;
    private int quotHeadId;
    private int itemId;
    private float quotQty;
    private float rate;
    private float taxableValue;
    private float taxValue;
    private int taxId;
    private float sgstPer;
    private float cgstPer;
    private int enqDetailId;
    private float cgstValue;
    private float sgstValue;
    private float otherCost;
    private int status;
    private int delStatus;
    private int exInt1;
    private int exInt2;
    private int exInt3;
    private String exVar1;
    private String exVar2;
    private String exVar3;
    private String exDate1;
    private String exDate2;
    private int exBool1;
    private int exBool2;
    private int exBool3;
    private float conFactor;
    private float convQty;
    private int quotUomId;
    private float igstPer;
    private float igstValue;
    private float total;
    private float tollCost;
    private float transCost;
    private float otherCostBeforeTax;
    private float otherCostAfterTax;
    private float royaltyRate;
    private float noOfKm;

    private String itemName;
    private String itemUom;

    public QuotDetail(int quotDetailId, int quotHeadId, int itemId, float quotQty, float rate, float taxableValue, float taxValue, int taxId, float sgstPer, float cgstPer, int enqDetailId, float cgstValue, float sgstValue, float otherCost, int status, int delStatus, String exVar1, String exVar2, String exVar3, String exDate1, String exDate2, float conFactor, float convQty, int quotUomId, float igstPer, float igstValue, float total, float tollCost, float transCost, float otherCostBeforeTax, float otherCostAfterTax, float royaltyRate, float noOfKm) {
        this.quotDetailId = quotDetailId;
        this.quotHeadId = quotHeadId;
        this.itemId = itemId;
        this.quotQty = quotQty;
        this.rate = rate;
        this.taxableValue = taxableValue;
        this.taxValue = taxValue;
        this.taxId = taxId;
        this.sgstPer = sgstPer;
        this.cgstPer = cgstPer;
        this.enqDetailId = enqDetailId;
        this.cgstValue = cgstValue;
        this.sgstValue = sgstValue;
        this.otherCost = otherCost;
        this.status = status;
        this.delStatus = delStatus;
        this.exVar1 = exVar1;
        this.exVar2 = exVar2;
        this.exVar3 = exVar3;
        this.exDate1 = exDate1;
        this.exDate2 = exDate2;
        this.conFactor = conFactor;
        this.convQty = convQty;
        this.quotUomId = quotUomId;
        this.igstPer = igstPer;
        this.igstValue = igstValue;
        this.total = total;
        this.tollCost = tollCost;
        this.transCost = transCost;
        this.otherCostBeforeTax = otherCostBeforeTax;
        this.otherCostAfterTax = otherCostAfterTax;
        this.royaltyRate = royaltyRate;
        this.noOfKm = noOfKm;
    }

    public int getQuotDetailId() {
        return quotDetailId;
    }

    public void setQuotDetailId(int quotDetailId) {
        this.quotDetailId = quotDetailId;
    }

    public int getQuotHeadId() {
        return quotHeadId;
    }

    public void setQuotHeadId(int quotHeadId) {
        this.quotHeadId = quotHeadId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getQuotQty() {
        return quotQty;
    }

    public void setQuotQty(float quotQty) {
        this.quotQty = quotQty;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getTaxableValue() {
        return taxableValue;
    }

    public void setTaxableValue(float taxableValue) {
        this.taxableValue = taxableValue;
    }

    public float getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(float taxValue) {
        this.taxValue = taxValue;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public float getSgstPer() {
        return sgstPer;
    }

    public void setSgstPer(float sgstPer) {
        this.sgstPer = sgstPer;
    }

    public float getCgstPer() {
        return cgstPer;
    }

    public void setCgstPer(float cgstPer) {
        this.cgstPer = cgstPer;
    }

    public int getEnqDetailId() {
        return enqDetailId;
    }

    public void setEnqDetailId(int enqDetailId) {
        this.enqDetailId = enqDetailId;
    }

    public float getCgstValue() {
        return cgstValue;
    }

    public void setCgstValue(float cgstValue) {
        this.cgstValue = cgstValue;
    }

    public float getSgstValue() {
        return sgstValue;
    }

    public void setSgstValue(float sgstValue) {
        this.sgstValue = sgstValue;
    }

    public float getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(float otherCost) {
        this.otherCost = otherCost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(int delStatus) {
        this.delStatus = delStatus;
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

    public int getExInt3() {
        return exInt3;
    }

    public void setExInt3(int exInt3) {
        this.exInt3 = exInt3;
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

    public String getExVar3() {
        return exVar3;
    }

    public void setExVar3(String exVar3) {
        this.exVar3 = exVar3;
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

    public int getExBool3() {
        return exBool3;
    }

    public void setExBool3(int exBool3) {
        this.exBool3 = exBool3;
    }

    public float getConFactor() {
        return conFactor;
    }

    public void setConFactor(float conFactor) {
        this.conFactor = conFactor;
    }

    public float getConvQty() {
        return convQty;
    }

    public void setConvQty(float convQty) {
        this.convQty = convQty;
    }

    public int getQuotUomId() {
        return quotUomId;
    }

    public void setQuotUomId(int quotUomId) {
        this.quotUomId = quotUomId;
    }

    public float getIgstPer() {
        return igstPer;
    }

    public void setIgstPer(float igstPer) {
        this.igstPer = igstPer;
    }

    public float getIgstValue() {
        return igstValue;
    }

    public void setIgstValue(float igstValue) {
        this.igstValue = igstValue;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTollCost() {
        return tollCost;
    }

    public void setTollCost(float tollCost) {
        this.tollCost = tollCost;
    }

    public float getTransCost() {
        return transCost;
    }

    public void setTransCost(float transCost) {
        this.transCost = transCost;
    }

    public float getOtherCostBeforeTax() {
        return otherCostBeforeTax;
    }

    public void setOtherCostBeforeTax(float otherCostBeforeTax) {
        this.otherCostBeforeTax = otherCostBeforeTax;
    }

    public float getOtherCostAfterTax() {
        return otherCostAfterTax;
    }

    public void setOtherCostAfterTax(float otherCostAfterTax) {
        this.otherCostAfterTax = otherCostAfterTax;
    }

    public float getRoyaltyRate() {
        return royaltyRate;
    }

    public void setRoyaltyRate(float royaltyRate) {
        this.royaltyRate = royaltyRate;
    }

    public float getNoOfKm() {
        return noOfKm;
    }

    public void setNoOfKm(float noOfKm) {
        this.noOfKm = noOfKm;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUom() {
        return itemUom;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    @Override
    public String toString() {
        return "QuotDetail{" +
                "quotDetailId=" + quotDetailId +
                ", quotHeadId=" + quotHeadId +
                ", itemId=" + itemId +
                ", quotQty=" + quotQty +
                ", rate=" + rate +
                ", taxableValue=" + taxableValue +
                ", taxValue=" + taxValue +
                ", taxId=" + taxId +
                ", sgstPer=" + sgstPer +
                ", cgstPer=" + cgstPer +
                ", enqDetailId=" + enqDetailId +
                ", cgstValue=" + cgstValue +
                ", sgstValue=" + sgstValue +
                ", otherCost=" + otherCost +
                ", status=" + status +
                ", delStatus=" + delStatus +
                ", exInt1=" + exInt1 +
                ", exInt2=" + exInt2 +
                ", exInt3=" + exInt3 +
                ", exVar1='" + exVar1 + '\'' +
                ", exVar2='" + exVar2 + '\'' +
                ", exVar3='" + exVar3 + '\'' +
                ", exDate1='" + exDate1 + '\'' +
                ", exDate2='" + exDate2 + '\'' +
                ", exBool1=" + exBool1 +
                ", exBool2=" + exBool2 +
                ", exBool3=" + exBool3 +
                ", conFactor=" + conFactor +
                ", convQty=" + convQty +
                ", quotUomId=" + quotUomId +
                ", igstPer=" + igstPer +
                ", igstValue=" + igstValue +
                ", total=" + total +
                ", tollCost=" + tollCost +
                ", transCost=" + transCost +
                ", otherCostBeforeTax=" + otherCostBeforeTax +
                ", otherCostAfterTax=" + otherCostAfterTax +
                ", royaltyRate=" + royaltyRate +
                ", noOfKm=" + noOfKm +
                ", itemName='" + itemName + '\'' +
                ", itemUom='" + itemUom + '\'' +
                '}';
    }
}
