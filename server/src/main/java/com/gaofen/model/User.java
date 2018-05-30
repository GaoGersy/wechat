package com.gaofen.model;

public class User {
    private String userName;

    private String passWord;

    private Long loginTime;

    private Integer status;
    private Integer autoStatus;

    public Integer getAutoStatus() {
        return autoStatus;
    }

    public void setAutoStatus(Integer autoStatus) {
        this.autoStatus = autoStatus;
    }

    public String getRandCode() {
        return randCode;
    }

    public void setRandCode(String randCode) {
        this.randCode = randCode;
    }

    private String openId;
    private String randCode;
    public User(String userName, String passWord, Long loginTime, Integer status, String openId) {
        this.userName = userName;
        this.passWord = passWord;
        this.loginTime = loginTime;
        this.status = status;
        this.openId = openId;
    }

    public User() {
        super();
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getpassWord() {
        return passWord;
    }

    public void setpassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public Long getloginTime() {
        return loginTime;
    }

    public void setloginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getstatus() {
        return status;
    }

    public void setstatus(Integer status) {
        this.status = status;
    }

    public String getopenId() {
        return openId;
    }

    public void setopenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", loginTime=" + loginTime +
                ", status=" + status +
                ", autoStatus=" + autoStatus +
                ", openId='" + openId + '\'' +
                ", randCode='" + randCode + '\'' +
                '}';
    }
}