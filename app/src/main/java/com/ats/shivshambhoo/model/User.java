package com.ats.shivshambhoo.model;

public class User {

    private int userId;
    private String usrName;
    private String userPass;
    private String usrMob;
    private int deptId;
    private int companyId;
    private int plantId;
    private String usrEmail;
    private String usrDob;
    private int roleId;
    private int sortNo;
    private int delStatus;
    private String deviceToken;
    private int exInt1;
    private int exInt2;
    private String exVar1;
    private String exVar2;
    private String exDate1;
    private String exDate2;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUsrMob() {
        return usrMob;
    }

    public void setUsrMob(String usrMob) {
        this.usrMob = usrMob;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrDob() {
        return usrDob;
    }

    public void setUsrDob(String usrDob) {
        this.usrDob = usrDob;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public int getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(int delStatus) {
        this.delStatus = delStatus;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", usrName='" + usrName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", usrMob='" + usrMob + '\'' +
                ", deptId=" + deptId +
                ", companyId=" + companyId +
                ", plantId=" + plantId +
                ", usrEmail='" + usrEmail + '\'' +
                ", usrDob='" + usrDob + '\'' +
                ", roleId=" + roleId +
                ", sortNo=" + sortNo +
                ", delStatus=" + delStatus +
                ", deviceToken='" + deviceToken + '\'' +
                ", exInt1=" + exInt1 +
                ", exInt2=" + exInt2 +
                ", exVar1='" + exVar1 + '\'' +
                ", exVar2='" + exVar2 + '\'' +
                ", exDate1='" + exDate1 + '\'' +
                ", exDate2='" + exDate2 + '\'' +
                '}';
    }
}
