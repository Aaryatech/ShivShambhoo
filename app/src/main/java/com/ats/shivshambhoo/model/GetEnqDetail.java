package com.ats.shivshambhoo.model;

public class GetEnqDetail {

    private int enqDetailId;

    private int enqHeadId;

    private int itemId;

    private float itemQty;

    private String itemUom;

    private int itemUomId;

    private int status;

    private int delStatus;

    private int quotId;

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

    private int enqUomId;
    private String enqDRemark;

    private String itemName;
    private String itemCode;
    private String uomName;


    public int getEnqDetailId() {
        return enqDetailId;
    }

    public void setEnqDetailId(int enqDetailId) {
        this.enqDetailId = enqDetailId;
    }

    public int getEnqHeadId() {
        return enqHeadId;
    }

    public void setEnqHeadId(int enqHeadId) {
        this.enqHeadId = enqHeadId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getItemQty() {
        return itemQty;
    }

    public void setItemQty(float itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemUom() {
        return itemUom;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public int getItemUomId() {
        return itemUomId;
    }

    public void setItemUomId(int itemUomId) {
        this.itemUomId = itemUomId;
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

    public int getQuotId() {
        return quotId;
    }

    public void setQuotId(int quotId) {
        this.quotId = quotId;
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

    public int getEnqUomId() {
        return enqUomId;
    }

    public void setEnqUomId(int enqUomId) {
        this.enqUomId = enqUomId;
    }

    public String getEnqDRemark() {
        return enqDRemark;
    }

    public void setEnqDRemark(String enqDRemark) {
        this.enqDRemark = enqDRemark;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    @Override
    public String toString() {
        return "GetEnqDetail{" +
                "enqDetailId=" + enqDetailId +
                ", enqHeadId=" + enqHeadId +
                ", itemId=" + itemId +
                ", itemQty=" + itemQty +
                ", itemUom='" + itemUom + '\'' +
                ", itemUomId=" + itemUomId +
                ", status=" + status +
                ", delStatus=" + delStatus +
                ", quotId=" + quotId +
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
                ", enqUomId=" + enqUomId +
                ", enqDRemark='" + enqDRemark + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", uomName='" + uomName + '\'' +
                '}';
    }
}
