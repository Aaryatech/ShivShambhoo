package com.ats.shivshambhoo.model;

public class GetOrderDetail {

    private int orderDetId;
    private int orderId;
    private int poId;
    private int itemId;
    private int poDetailId;
    private float orderQty;
    private float orderRate;
    private float total;
    private int delStatus;
    private float remOrdQty;
    private String uomName;
    private int uomId;
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
    private int status;
    private float poRate;
    private float poQty;
    private float poConsumeQty;
    private float poTotal;
    private float poRemainingQty;
    private String itemName;
    private String itemCode;
    private String poDate;
    private String poNo;

    public int getOrderDetId() {
        return orderDetId;
    }

    public void setOrderDetId(int orderDetId) {
        this.orderDetId = orderDetId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getPoDetailId() {
        return poDetailId;
    }

    public void setPoDetailId(int poDetailId) {
        this.poDetailId = poDetailId;
    }

    public float getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(float orderQty) {
        this.orderQty = orderQty;
    }

    public float getOrderRate() {
        return orderRate;
    }

    public void setOrderRate(float orderRate) {
        this.orderRate = orderRate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(int delStatus) {
        this.delStatus = delStatus;
    }

    public float getRemOrdQty() {
        return remOrdQty;
    }

    public void setRemOrdQty(float remOrdQty) {
        this.remOrdQty = remOrdQty;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public int getUomId() {
        return uomId;
    }

    public void setUomId(int uomId) {
        this.uomId = uomId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public float getPoTotal() {
        return poTotal;
    }

    public void setPoTotal(float poTotal) {
        this.poTotal = poTotal;
    }

    public float getPoRemainingQty() {
        return poRemainingQty;
    }

    public void setPoRemainingQty(float poRemainingQty) {
        this.poRemainingQty = poRemainingQty;
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

    public String getPoDate() {
        return poDate;
    }

    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    @Override
    public String toString() {
        return "GetOrderDetail{" +
                "orderDetId=" + orderDetId +
                ", orderId=" + orderId +
                ", poId=" + poId +
                ", itemId=" + itemId +
                ", poDetailId=" + poDetailId +
                ", orderQty=" + orderQty +
                ", orderRate=" + orderRate +
                ", total=" + total +
                ", delStatus=" + delStatus +
                ", remOrdQty=" + remOrdQty +
                ", uomName='" + uomName + '\'' +
                ", uomId=" + uomId +
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
                ", status=" + status +
                ", poRate=" + poRate +
                ", poQty=" + poQty +
                ", poConsumeQty=" + poConsumeQty +
                ", poTotal=" + poTotal +
                ", poRemainingQty=" + poRemainingQty +
                ", itemName='" + itemName + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", poDate='" + poDate + '\'' +
                ", poNo='" + poNo + '\'' +
                '}';
    }
}
