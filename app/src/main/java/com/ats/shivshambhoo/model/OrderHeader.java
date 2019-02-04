package com.ats.shivshambhoo.model;

import java.util.List;

public class OrderHeader {

    private int orderId;
    private int plantId;
    private int custId;
    private int poId;
    private int projId;
    private String deliveryDate;
    private String orderDate;
    private String prodDate;
    private float orderValue;
    private String orderNo;
    private float total;
    private int isTaxIncluding;
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
    private int status;
    List<OrderDetail> orderDetailList;

    public OrderHeader(int orderId, int plantId, int custId, int poId, int projId, String deliveryDate, String orderDate, String prodDate, float orderValue, String orderNo, float total, int isTaxIncluding, int delStatus, int exInt1,int status, List<OrderDetail> orderDetailList) {
        this.orderId = orderId;
        this.plantId = plantId;
        this.custId = custId;
        this.poId = poId;
        this.projId = projId;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.prodDate = prodDate;
        this.orderValue = orderValue;
        this.orderNo = orderNo;
        this.total = total;
        this.isTaxIncluding = isTaxIncluding;
        this.delStatus = delStatus;
        this.exInt1=exInt1;
        this.status = status;
        this.orderDetailList = orderDetailList;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getPoId() {
        return poId;
    }

    public void setPoId(int poId) {
        this.poId = poId;
    }

    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getProdDate() {
        return prodDate;
    }

    public void setProdDate(String prodDate) {
        this.prodDate = prodDate;
    }

    public float getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(float orderValue) {
        this.orderValue = orderValue;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getIsTaxIncluding() {
        return isTaxIncluding;
    }

    public void setIsTaxIncluding(int isTaxIncluding) {
        this.isTaxIncluding = isTaxIncluding;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public String toString() {
        return "OrderHeader{" +
                "orderId=" + orderId +
                ", plantId=" + plantId +
                ", custId=" + custId +
                ", poId=" + poId +
                ", projId=" + projId +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", prodDate='" + prodDate + '\'' +
                ", orderValue=" + orderValue +
                ", orderNo='" + orderNo + '\'' +
                ", total=" + total +
                ", isTaxIncluding=" + isTaxIncluding +
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
                ", status=" + status +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}
