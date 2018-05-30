package com.gaofen.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProductOrder {
    private long ID;

    private String userId;

    private Date ORDERDATE;

    private Date AUDITDATE;

    private BigDecimal ISDELL;

    private String USERNAME;

    private String DATASIZE;

    private String SERIALID;

    private BigDecimal DISSTATE;

    private String CHECKID;

    private String CHECKNAME;

    private String REMARK;

    private String SPECIFICATIONS;

    private String DATAAPPLICATION;

    private String DATALEVEL;

    private BigDecimal ORDERSOURCE;

    private BigDecimal ISCUSTOM;

    private BigDecimal STATE;


    public ProductOrder(long ID, String userId, Date ORDERDATE, Date AUDITDATE, BigDecimal ISDELL, String USERNAME, String DATASIZE, String SERIALID, BigDecimal DISSTATE, String CHECKID, String CHECKNAME, String REMARK, String SPECIFICATIONS, String DATAAPPLICATION, String DATALEVEL, BigDecimal ORDERSOURCE, BigDecimal ISCUSTOM, BigDecimal STATE) {
        this.ID = ID;
        this.userId = userId;
        this.ORDERDATE = ORDERDATE;
        this.AUDITDATE = AUDITDATE;
        this.ISDELL = ISDELL;
        this.USERNAME = USERNAME;
        this.DATASIZE = DATASIZE;
        this.SERIALID = SERIALID;
        this.DISSTATE = DISSTATE;
        this.CHECKID = CHECKID;
        this.CHECKNAME = CHECKNAME;
        this.REMARK = REMARK;
        this.SPECIFICATIONS = SPECIFICATIONS;
        this.DATAAPPLICATION = DATAAPPLICATION;
        this.DATALEVEL = DATALEVEL;
        this.ORDERSOURCE = ORDERSOURCE;
        this.ISCUSTOM = ISCUSTOM;
        this.STATE = STATE;
    }

    public ProductOrder() {
        super();
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getORDERDATE() {
        return ORDERDATE;
    }

    public void setORDERDATE(Date ORDERDATE) {
        this.ORDERDATE = ORDERDATE;
    }

    public Date getAUDITDATE() {
        return AUDITDATE;
    }

    public void setAUDITDATE(Date AUDITDATE) {
        this.AUDITDATE = AUDITDATE;
    }

    public BigDecimal getISDELL() {
        return ISDELL;
    }

    public void setISDELL(BigDecimal ISDELL) {
        this.ISDELL = ISDELL;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME == null ? null : USERNAME.trim();
    }

    public String getDATASIZE() {
        return DATASIZE;
    }

    public void setDATASIZE(String DATASIZE) {
        this.DATASIZE = DATASIZE == null ? null : DATASIZE.trim();
    }

    public String getSERIALID() {
        return SERIALID;
    }

    public void setSERIALID(String SERIALID) {
        this.SERIALID = SERIALID == null ? null : SERIALID.trim();
    }

    public BigDecimal getDISSTATE() {
        return DISSTATE;
    }

    public void setDISSTATE(BigDecimal DISSTATE) {
        this.DISSTATE = DISSTATE;
    }

    public String getCHECKID() {
        return CHECKID;
    }

    public void setCHECKID(String CHECKID) {
        this.CHECKID = CHECKID == null ? null : CHECKID.trim();
    }

    public String getCHECKNAME() {
        return CHECKNAME;
    }

    public void setCHECKNAME(String CHECKNAME) {
        this.CHECKNAME = CHECKNAME == null ? null : CHECKNAME.trim();
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK == null ? null : REMARK.trim();
    }

    public String getSPECIFICATIONS() {
        return SPECIFICATIONS;
    }

    public void setSPECIFICATIONS(String SPECIFICATIONS) {
        this.SPECIFICATIONS = SPECIFICATIONS == null ? null : SPECIFICATIONS.trim();
    }

    public String getDATAAPPLICATION() {
        return DATAAPPLICATION;
    }

    public void setDATAAPPLICATION(String DATAAPPLICATION) {
        this.DATAAPPLICATION = DATAAPPLICATION == null ? null : DATAAPPLICATION.trim();
    }

    public String getDATALEVEL() {
        return DATALEVEL;
    }

    public void setDATALEVEL(String DATALEVEL) {
        this.DATALEVEL = DATALEVEL == null ? null : DATALEVEL.trim();
    }

    public BigDecimal getORDERSOURCE() {
        return ORDERSOURCE;
    }

    public void setORDERSOURCE(BigDecimal ORDERSOURCE) {
        this.ORDERSOURCE = ORDERSOURCE;
    }

    public BigDecimal getISCUSTOM() {
        return ISCUSTOM;
    }

    public void setISCUSTOM(BigDecimal ISCUSTOM) {
        this.ISCUSTOM = ISCUSTOM;
    }

    public BigDecimal getSTATE() {
        return STATE;
    }

    public void setSTATE(BigDecimal STATE) {
        this.STATE = STATE;
    }
}