package com.gaofen.model;

public class TbBasMetaBlob {
    private Short fDataid;

    private String fState;

    public TbBasMetaBlob(Short fDataid, String fState) {
        this.fDataid = fDataid;
        this.fState = fState;
    }

    public TbBasMetaBlob() {
        super();
    }

    public Short getfDataid() {
        return fDataid;
    }

    public void setfDataid(Short fDataid) {
        this.fDataid = fDataid;
    }

    public String getfState() {
        return fState;
    }

    public void setfState(String fState) {
        this.fState = fState == null ? null : fState.trim();
    }
}