package com.gaofen.model;


import java.util.Date;

public class BasSatelliteTerm {
    private long DATAID;

    private String SATELLITEID;

    private String SENSORID;

    private Date SCENEDATE;

    private String PRODUCTLEVEL;

    private String RECSTATIONID;

    private long OVERALLQUALITY;

    private long SCENEPATH;

    private long SCENEROW;

    private String PRODUCTNAME;

    private long STATE;

    private String PRODUCTPATH;

    private String FILESIZE;

    private String PICTUREFORMAT;

    private long TOPLEFTLATITUDE;

    private long TOPLEFTLONGITUDE;

    private long TOPRIGHTLATITUDE;

    private long TOPRIGHTLONGITUDE;

    private long BOTTOMRIGHTLATITUDE;

    private long BOTTOMRIGHTLONGITUDE;

    private long BOTTOMLEFTLATITUDE;

    private long BOTTOMLEFTLONGITUDE;

    private Date INSTORAGEDATE;

    private String TESTSS;

    private long MYOBJSTATE;

    private long IMGOPERATESTATE;

    private long SCENEID;

    private String CATALOGID;

    private Date PUSHDATE;

    private long PRODUCTID;

    private String MOSAICPATH;

    public BasSatelliteTerm(long DATAID, String SATELLITEID, String SENSORID, Date SCENEDATE, String PRODUCTLEVEL, String RECSTATIONID, long OVERALLQUALITY, long SCENEPATH, long SCENEROW, String PRODUCTNAME, long STATE, String PRODUCTPATH, String FILESIZE, String PICTUREFORMAT, long TOPLEFTLATITUDE, long TOPLEFTLONGITUDE, long TOPRIGHTLATITUDE, long TOPRIGHTLONGITUDE, long BOTTOMRIGHTLATITUDE, long BOTTOMRIGHTLONGITUDE, long BOTTOMLEFTLATITUDE, long BOTTOMLEFTLONGITUDE, Date INSTORAGEDATE, String TESTSS, long MYOBJSTATE, long IMGOPERATESTATE, long SCENEID, String CATALOGID, Date PUSHDATE, long PRODUCTID, String MOSAICPATH) {
        this.DATAID = DATAID;
        this.SATELLITEID = SATELLITEID;
        this.SENSORID = SENSORID;
        this.SCENEDATE = SCENEDATE;
        this.PRODUCTLEVEL = PRODUCTLEVEL;
        this.RECSTATIONID = RECSTATIONID;
        this.OVERALLQUALITY = OVERALLQUALITY;
        this.SCENEPATH = SCENEPATH;
        this.SCENEROW = SCENEROW;
        this.PRODUCTNAME = PRODUCTNAME;
        this.STATE = STATE;
        this.PRODUCTPATH = PRODUCTPATH;
        this.FILESIZE = FILESIZE;
        this.PICTUREFORMAT = PICTUREFORMAT;
        this.TOPLEFTLATITUDE = TOPLEFTLATITUDE;
        this.TOPLEFTLONGITUDE = TOPLEFTLONGITUDE;
        this.TOPRIGHTLATITUDE = TOPRIGHTLATITUDE;
        this.TOPRIGHTLONGITUDE = TOPRIGHTLONGITUDE;
        this.BOTTOMRIGHTLATITUDE = BOTTOMRIGHTLATITUDE;
        this.BOTTOMRIGHTLONGITUDE = BOTTOMRIGHTLONGITUDE;
        this.BOTTOMLEFTLATITUDE = BOTTOMLEFTLATITUDE;
        this.BOTTOMLEFTLONGITUDE = BOTTOMLEFTLONGITUDE;
        this.INSTORAGEDATE = INSTORAGEDATE;
        this.TESTSS = TESTSS;
        this.MYOBJSTATE = MYOBJSTATE;
        this.IMGOPERATESTATE = IMGOPERATESTATE;
        this.SCENEID = SCENEID;
        this.CATALOGID = CATALOGID;
        this.PUSHDATE = PUSHDATE;
        this.PRODUCTID = PRODUCTID;
        this.MOSAICPATH = MOSAICPATH;
    }

    public BasSatelliteTerm() {
        super();
    }

    public long getDATAID() {
        return DATAID;
    }

    public void setDATAID(long DATAID) {
        this.DATAID = DATAID;
    }

    public String getSATELLITEID() {
        return SATELLITEID;
    }

    public void setSATELLITEID(String SATELLITEID) {
        this.SATELLITEID = SATELLITEID == null ? null : SATELLITEID.trim();
    }

    public String getSENSORID() {
        return SENSORID;
    }

    public void setSENSORID(String SENSORID) {
        this.SENSORID = SENSORID == null ? null : SENSORID.trim();
    }

    public Date getSCENEDATE() {
        return SCENEDATE;
    }

    public void setSCENEDATE(Date SCENEDATE) {
        this.SCENEDATE = SCENEDATE;
    }

    public String getPRODUCTLEVEL() {
        return PRODUCTLEVEL;
    }

    public void setPRODUCTLEVEL(String PRODUCTLEVEL) {
        this.PRODUCTLEVEL = PRODUCTLEVEL == null ? null : PRODUCTLEVEL.trim();
    }

    public String getRECSTATIONID() {
        return RECSTATIONID;
    }

    public void setRECSTATIONID(String RECSTATIONID) {
        this.RECSTATIONID = RECSTATIONID == null ? null : RECSTATIONID.trim();
    }

    public long getOVERALLQUALITY() {
        return OVERALLQUALITY;
    }

    public void setOVERALLQUALITY(long OVERALLQUALITY) {
        this.OVERALLQUALITY = OVERALLQUALITY;
    }

    public long getSCENEPATH() {
        return SCENEPATH;
    }

    public void setSCENEPATH(long SCENEPATH) {
        this.SCENEPATH = SCENEPATH;
    }

    public long getSCENEROW() {
        return SCENEROW;
    }

    public void setSCENEROW(long SCENEROW) {
        this.SCENEROW = SCENEROW;
    }

    public String getPRODUCTNAME() {
        return PRODUCTNAME;
    }

    public void setPRODUCTNAME(String PRODUCTNAME) {
        this.PRODUCTNAME = PRODUCTNAME == null ? null : PRODUCTNAME.trim();
    }

    public long getSTATE() {
        return STATE;
    }

    public void setSTATE(long STATE) {
        this.STATE = STATE;
    }

    public String getPRODUCTPATH() {
        return PRODUCTPATH;
    }

    public void setPRODUCTPATH(String PRODUCTPATH) {
        this.PRODUCTPATH = PRODUCTPATH == null ? null : PRODUCTPATH.trim();
    }

    public String getFILESIZE() {
        return FILESIZE;
    }

    public void setFILESIZE(String FILESIZE) {
        this.FILESIZE = FILESIZE == null ? null : FILESIZE.trim();
    }

    public String getPICTUREFORMAT() {
        return PICTUREFORMAT;
    }

    public void setPICTUREFORMAT(String PICTUREFORMAT) {
        this.PICTUREFORMAT = PICTUREFORMAT == null ? null : PICTUREFORMAT.trim();
    }

    public long getTOPLEFTLATITUDE() {
        return TOPLEFTLATITUDE;
    }

    public void setTOPLEFTLATITUDE(long TOPLEFTLATITUDE) {
        this.TOPLEFTLATITUDE = TOPLEFTLATITUDE;
    }

    public long getTOPLEFTLONGITUDE() {
        return TOPLEFTLONGITUDE;
    }

    public void setTOPLEFTLONGITUDE(long TOPLEFTLONGITUDE) {
        this.TOPLEFTLONGITUDE = TOPLEFTLONGITUDE;
    }

    public long getTOPRIGHTLATITUDE() {
        return TOPRIGHTLATITUDE;
    }

    public void setTOPRIGHTLATITUDE(long TOPRIGHTLATITUDE) {
        this.TOPRIGHTLATITUDE = TOPRIGHTLATITUDE;
    }

    public long getTOPRIGHTLONGITUDE() {
        return TOPRIGHTLONGITUDE;
    }

    public void setTOPRIGHTLONGITUDE(long TOPRIGHTLONGITUDE) {
        this.TOPRIGHTLONGITUDE = TOPRIGHTLONGITUDE;
    }

    public long getBOTTOMRIGHTLATITUDE() {
        return BOTTOMRIGHTLATITUDE;
    }

    public void setBOTTOMRIGHTLATITUDE(long BOTTOMRIGHTLATITUDE) {
        this.BOTTOMRIGHTLATITUDE = BOTTOMRIGHTLATITUDE;
    }

    public long getBOTTOMRIGHTLONGITUDE() {
        return BOTTOMRIGHTLONGITUDE;
    }

    public void setBOTTOMRIGHTLONGITUDE(long BOTTOMRIGHTLONGITUDE) {
        this.BOTTOMRIGHTLONGITUDE = BOTTOMRIGHTLONGITUDE;
    }

    public long getBOTTOMLEFTLATITUDE() {
        return BOTTOMLEFTLATITUDE;
    }

    public void setBOTTOMLEFTLATITUDE(long BOTTOMLEFTLATITUDE) {
        this.BOTTOMLEFTLATITUDE = BOTTOMLEFTLATITUDE;
    }

    public long getBOTTOMLEFTLONGITUDE() {
        return BOTTOMLEFTLONGITUDE;
    }

    public void setBOTTOMLEFTLONGITUDE(long BOTTOMLEFTLONGITUDE) {
        this.BOTTOMLEFTLONGITUDE = BOTTOMLEFTLONGITUDE;
    }

    public Date getINSTORAGEDATE() {
        return INSTORAGEDATE;
    }

    public void setINSTORAGEDATE(Date INSTORAGEDATE) {
        this.INSTORAGEDATE = INSTORAGEDATE;
    }

    public String getTESTSS() {
        return TESTSS;
    }

    public void setTESTSS(String TESTSS) {
        this.TESTSS = TESTSS == null ? null : TESTSS.trim();
    }

    public long getMYOBJSTATE() {
        return MYOBJSTATE;
    }

    public void setMYOBJSTATE(long MYOBJSTATE) {
        this.MYOBJSTATE = MYOBJSTATE;
    }

    public long getIMGOPERATESTATE() {
        return IMGOPERATESTATE;
    }

    public void setIMGOPERATESTATE(long IMGOPERATESTATE) {
        this.IMGOPERATESTATE = IMGOPERATESTATE;
    }

    public long getSCENEID() {
        return SCENEID;
    }

    public void setSCENEID(long SCENEID) {
        this.SCENEID = SCENEID;
    }

    public String getCATALOGID() {
        return CATALOGID;
    }

    public void setCATALOGID(String CATALOGID) {
        this.CATALOGID = CATALOGID == null ? null : CATALOGID.trim();
    }

    public Date getPUSHDATE() {
        return PUSHDATE;
    }

    public void setPUSHDATE(Date PUSHDATE) {
        this.PUSHDATE = PUSHDATE;
    }

    public long getPRODUCTID() {
        return PRODUCTID;
    }

    public void setPRODUCTID(long PRODUCTID) {
        this.PRODUCTID = PRODUCTID;
    }

    public String getMOSAICPATH() {
        return MOSAICPATH;
    }

    public void setMOSAICPATH(String MOSAICPATH) {
        this.MOSAICPATH = MOSAICPATH == null ? null : MOSAICPATH.trim();
    }
}