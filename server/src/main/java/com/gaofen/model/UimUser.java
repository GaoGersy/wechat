package com.gaofen.model;

import java.math.BigDecimal;
import java.util.Date;

public class UimUser {
    private String USERID;

    private String PWD;

    private String MEMO;

    private Date ADD_TIME;

    private BigDecimal ISLOCK;

    private String SYSTEM_KEY;

    private String CA;

    private String ORGID;

    private String USERNAME;

    private String EMAIL;

    private String PHONE;

    private BigDecimal ISPORTALUSER;

    private BigDecimal FCOUNTYCODE;

    private String SEX;

    private String BIRTH;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String CERNO;

    private String FAX;

    private BigDecimal SHOWINDEX;

    private String DUTY;

    private String TELEPHONE;

    private String FAMILY_ADDRESS;

    private String COMPANY_ADDRESS;

    private String ISADD;

    private String ISCOMM;

    private String THEME_ID;

    private BigDecimal VISITECOUNT;

    private String REASON;

    private String REPLY;
    private String token;
    private BigDecimal MOBILEPHONE;

    public UimUser(String USERID, String PWD, String MEMO, Date ADD_TIME, BigDecimal ISLOCK, String SYSTEM_KEY, String CA, String ORGID, String USERNAME, String EMAIL, String PHONE, BigDecimal ISPORTALUSER, BigDecimal FCOUNTYCODE, String SEX, String BIRTH, String CERNO, String FAX, BigDecimal SHOWINDEX, String DUTY, String TELEPHONE, String FAMILY_ADDRESS, String COMPANY_ADDRESS, String ISADD, String ISCOMM, String THEME_ID, BigDecimal VISITECOUNT, String REASON, String REPLY, BigDecimal MOBILEPHONE) {
        this.USERID = USERID;
        this.PWD = PWD;
        this.MEMO = MEMO;
        this.ADD_TIME = ADD_TIME;
        this.ISLOCK = ISLOCK;
        this.SYSTEM_KEY = SYSTEM_KEY;
        this.CA = CA;
        this.ORGID = ORGID;
        this.USERNAME = USERNAME;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
        this.ISPORTALUSER = ISPORTALUSER;
        this.FCOUNTYCODE = FCOUNTYCODE;
        this.SEX = SEX;
        this.BIRTH = BIRTH;
        this.CERNO = CERNO;
        this.FAX = FAX;
        this.SHOWINDEX = SHOWINDEX;
        this.DUTY = DUTY;
        this.TELEPHONE = TELEPHONE;
        this.FAMILY_ADDRESS = FAMILY_ADDRESS;
        this.COMPANY_ADDRESS = COMPANY_ADDRESS;
        this.ISADD = ISADD;
        this.ISCOMM = ISCOMM;
        this.THEME_ID = THEME_ID;
        this.VISITECOUNT = VISITECOUNT;
        this.REASON = REASON;
        this.REPLY = REPLY;
        this.MOBILEPHONE = MOBILEPHONE;
    }

    public UimUser() {
        super();
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID == null ? null : USERID.trim();
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD == null ? null : PWD.trim();
    }

    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO == null ? null : MEMO.trim();
    }

    public Date getADD_TIME() {
        return ADD_TIME;
    }

    public void setADD_TIME(Date ADD_TIME) {
        this.ADD_TIME = ADD_TIME;
    }

    public BigDecimal getISLOCK() {
        return ISLOCK;
    }

    public void setISLOCK(BigDecimal ISLOCK) {
        this.ISLOCK = ISLOCK;
    }

    public String getSYSTEM_KEY() {
        return SYSTEM_KEY;
    }

    public void setSYSTEM_KEY(String SYSTEM_KEY) {
        this.SYSTEM_KEY = SYSTEM_KEY == null ? null : SYSTEM_KEY.trim();
    }

    public String getCA() {
        return CA;
    }

    public void setCA(String CA) {
        this.CA = CA == null ? null : CA.trim();
    }

    public String getORGID() {
        return ORGID;
    }

    public void setORGID(String ORGID) {
        this.ORGID = ORGID == null ? null : ORGID.trim();
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME == null ? null : USERNAME.trim();
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL == null ? null : EMAIL.trim();
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE == null ? null : PHONE.trim();
    }

    public BigDecimal getISPORTALUSER() {
        return ISPORTALUSER;
    }

    public void setISPORTALUSER(BigDecimal ISPORTALUSER) {
        this.ISPORTALUSER = ISPORTALUSER;
    }

    public BigDecimal getFCOUNTYCODE() {
        return FCOUNTYCODE;
    }

    public void setFCOUNTYCODE(BigDecimal FCOUNTYCODE) {
        this.FCOUNTYCODE = FCOUNTYCODE;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX == null ? null : SEX.trim();
    }

    public String getBIRTH() {
        return BIRTH;
    }

    public void setBIRTH(String BIRTH) {
        this.BIRTH = BIRTH == null ? null : BIRTH.trim();
    }

    public String getCERNO() {
        return CERNO;
    }

    public void setCERNO(String CERNO) {
        this.CERNO = CERNO == null ? null : CERNO.trim();
    }

    public String getFAX() {
        return FAX;
    }

    public void setFAX(String FAX) {
        this.FAX = FAX == null ? null : FAX.trim();
    }

    public BigDecimal getSHOWINDEX() {
        return SHOWINDEX;
    }

    public void setSHOWINDEX(BigDecimal SHOWINDEX) {
        this.SHOWINDEX = SHOWINDEX;
    }

    public String getDUTY() {
        return DUTY;
    }

    public void setDUTY(String DUTY) {
        this.DUTY = DUTY == null ? null : DUTY.trim();
    }

    public String getTELEPHONE() {
        return TELEPHONE;
    }

    public void setTELEPHONE(String TELEPHONE) {
        this.TELEPHONE = TELEPHONE == null ? null : TELEPHONE.trim();
    }

    public String getFAMILY_ADDRESS() {
        return FAMILY_ADDRESS;
    }

    public void setFAMILY_ADDRESS(String FAMILY_ADDRESS) {
        this.FAMILY_ADDRESS = FAMILY_ADDRESS == null ? null : FAMILY_ADDRESS.trim();
    }

    public String getCOMPANY_ADDRESS() {
        return COMPANY_ADDRESS;
    }

    public void setCOMPANY_ADDRESS(String COMPANY_ADDRESS) {
        this.COMPANY_ADDRESS = COMPANY_ADDRESS == null ? null : COMPANY_ADDRESS.trim();
    }

    public String getISADD() {
        return ISADD;
    }

    public void setISADD(String ISADD) {
        this.ISADD = ISADD == null ? null : ISADD.trim();
    }

    public String getISCOMM() {
        return ISCOMM;
    }

    public void setISCOMM(String ISCOMM) {
        this.ISCOMM = ISCOMM == null ? null : ISCOMM.trim();
    }

    public String getTHEME_ID() {
        return THEME_ID;
    }

    public void setTHEME_ID(String THEME_ID) {
        this.THEME_ID = THEME_ID == null ? null : THEME_ID.trim();
    }

    public BigDecimal getVISITECOUNT() {
        return VISITECOUNT;
    }

    public void setVISITECOUNT(BigDecimal VISITECOUNT) {
        this.VISITECOUNT = VISITECOUNT;
    }

    public String getREASON() {
        return REASON;
    }

    public void setREASON(String REASON) {
        this.REASON = REASON == null ? null : REASON.trim();
    }

    public String getREPLY() {
        return REPLY;
    }

    public void setREPLY(String REPLY) {
        this.REPLY = REPLY == null ? null : REPLY.trim();
    }

    public BigDecimal getMOBILEPHONE() {
        return MOBILEPHONE;
    }

    public void setMOBILEPHONE(BigDecimal MOBILEPHONE) {
        this.MOBILEPHONE = MOBILEPHONE;
    }
}