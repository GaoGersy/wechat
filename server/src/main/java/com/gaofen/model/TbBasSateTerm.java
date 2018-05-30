package com.gaofen.model;

import java.util.Date;

public class TbBasSateTerm {
    private double dataid;

    private String satelliteid;

    private String sensorid;

    private Date scenedate;

    private String productlevel;

    private String recstationid;

    private double overallquality;

    private double scenepath;

    private double scenerow;

    private String productname;

    private double state;

    private String productpath;

    private String filesize;

    private String pictureformat;

    private double topleftlatitude;

    private double topleftlongitude;

    private double toprightlatitude;

    private double toprightlongitude;

    private double bottomrightlatitude;

    private double bottomrightlongitude;

    private double bottomleftlatitude;

    private double bottomleftlongitude;

    private Date instoragedate;

    private String testss;

    private double myobjstate;

    private double imgoperatestate;

    private double sceneid;

    private String catalogid;

    private Date pushdate;

    private double productid;

    private String mosaicpath;

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    private String     producttype;

    public TbBasSateTerm(double dataid, String satelliteid, String sensorid, Date scenedate, String productlevel, String recstationid, double overallquality, double scenepath, double scenerow, String productname, double state, String productpath, String filesize, String pictureformat, double topleftlatitude, double topleftlongitude, double toprightlatitude, double toprightlongitude, double bottomrightlatitude, double bottomrightlongitude, double bottomleftlatitude, double bottomleftlongitude, Date instoragedate, String testss, double myobjstate, double imgoperatestate, double sceneid, String catalogid, Date pushdate, double productid, String mosaicpath, String producttype) {
        this.dataid = dataid;
        this.satelliteid = satelliteid;
        this.sensorid = sensorid;
        this.scenedate = scenedate;
        this.productlevel = productlevel;
        this.recstationid = recstationid;
        this.overallquality = overallquality;
        this.scenepath = scenepath;
        this.scenerow = scenerow;
        this.productname = productname;
        this.state = state;
        this.productpath = productpath;
        this.filesize = filesize;
        this.pictureformat = pictureformat;
        this.topleftlatitude = topleftlatitude;
        this.topleftlongitude = topleftlongitude;
        this.toprightlatitude = toprightlatitude;
        this.toprightlongitude = toprightlongitude;
        this.bottomrightlatitude = bottomrightlatitude;
        this.bottomrightlongitude = bottomrightlongitude;
        this.bottomleftlatitude = bottomleftlatitude;
        this.bottomleftlongitude = bottomleftlongitude;
        this.instoragedate = instoragedate;
        this.testss = testss;
        this.myobjstate = myobjstate;
        this.imgoperatestate = imgoperatestate;
        this.sceneid = sceneid;
        this.catalogid = catalogid;
        this.pushdate = pushdate;
        this.productid = productid;
        this.mosaicpath = mosaicpath;
        this.producttype = producttype;
    }

    public TbBasSateTerm() {
        super();
    }

    public double getdataid() {
        return dataid;
    }

    public void setdataid(double dataid) {
        this.dataid = dataid;
    }

    public String getSatelliteid() {
        return satelliteid;
    }

    public void setSatelliteid(String satelliteid) {
        this.satelliteid = satelliteid == null ? null : satelliteid.trim();
    }

    public String getSensorid() {
        return sensorid;
    }

    public void setSensorid(String sensorid) {
        this.sensorid = sensorid == null ? null : sensorid.trim();
    }

    public Date getScenedate() {
        return scenedate;
    }

    public void setScenedate(Date scenedate) {
        this.scenedate = scenedate;
    }

    public String getProductlevel() {
        return productlevel;
    }

    public void setProductlevel(String productlevel) {
        this.productlevel = productlevel == null ? null : productlevel.trim();
    }

    public String getRecstationid() {
        return recstationid;
    }

    public void setRecstationid(String recstationid) {
        this.recstationid = recstationid == null ? null : recstationid.trim();
    }

    public double getOverallquality() {
        return overallquality;
    }

    public void setOverallquality(double overallquality) {
        this.overallquality = overallquality;
    }

    public double getScenepath() {
        return scenepath;
    }

    public void setScenepath(double scenepath) {
        this.scenepath = scenepath;
    }

    public double getScenerow() {
        return scenerow;
    }

    public void setScenerow(double scenerow) {
        this.scenerow = scenerow;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public double getState() {
        return state;
    }

    public void setState(double state) {
        this.state = state;
    }

    public String getProductpath() {
        return productpath;
    }

    public void setProductpath(String productpath) {
        this.productpath = productpath == null ? null : productpath.trim();
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize == null ? null : filesize.trim();
    }

    public String getPictureformat() {
        return pictureformat;
    }

    public void setPictureformat(String pictureformat) {
        this.pictureformat = pictureformat == null ? null : pictureformat.trim();
    }

    public double getTopleftlatitude() {
        return topleftlatitude;
    }

    public void setTopleftlatitude(double topleftlatitude) {
        this.topleftlatitude = topleftlatitude;
    }

    public double gettopleftlongitude() {
        return topleftlongitude;
    }

    public void settopleftlongitude(double topleftlongitude) {
        this.topleftlongitude = topleftlongitude;
    }

    public double getToprightlatitude() {
        return toprightlatitude;
    }

    public void setToprightlatitude(double toprightlatitude) {
        this.toprightlatitude = toprightlatitude;
    }

    public double gettoprightlongitude() {
        return toprightlongitude;
    }

    public void settoprightlongitude(double toprightlongitude) {
        this.toprightlongitude = toprightlongitude;
    }

    public double getBottomrightlatitude() {
        return bottomrightlatitude;
    }

    public void setBottomrightlatitude(double bottomrightlatitude) {
        this.bottomrightlatitude = bottomrightlatitude;
    }

    public double getbottomrightlongitude() {
        return bottomrightlongitude;
    }

    public void setbottomrightlongitude(double bottomrightlongitude) {
        this.bottomrightlongitude = bottomrightlongitude;
    }

    public double getBottomleftlatitude() {
        return bottomleftlatitude;
    }

    public void setBottomleftlatitude(double bottomleftlatitude) {
        this.bottomleftlatitude = bottomleftlatitude;
    }

    public double getbottomleftlongitude() {
        return bottomleftlongitude;
    }

    public void setbottomleftlongitude(double bottomleftlongitude) {
        this.bottomleftlongitude = bottomleftlongitude;
    }

    public Date getinstoragedate() {
        return instoragedate;
    }

    public void setinstoragedate(Date instoragedate) {
        this.instoragedate = instoragedate;
    }

    public String getTestss() {
        return testss;
    }

    public void setTestss(String testss) {
        this.testss = testss == null ? null : testss.trim();
    }

    public double getMyobjstate() {
        return myobjstate;
    }

    public void setMyobjstate(double myobjstate) {
        this.myobjstate = myobjstate;
    }

    public double getimgoperatestate() {
        return imgoperatestate;
    }

    public void setimgoperatestate(double imgoperatestate) {
        this.imgoperatestate = imgoperatestate;
    }

    public double getSceneid() {
        return sceneid;
    }

    public void setSceneid(double sceneid) {
        this.sceneid = sceneid;
    }

    public String getCatalogid() {
        return catalogid;
    }

    public void setCatalogid(String catalogid) {
        this.catalogid = catalogid == null ? null : catalogid.trim();
    }

    public Date getPushdate() {
        return pushdate;
    }

    public void setPushdate(Date pushdate) {
        this.pushdate = pushdate;
    }

    public double getProductid() {
        return productid;
    }

    public void setProductid(double productid) {
        this.productid = productid;
    }

    public String getMosaicpath() {
        return mosaicpath;
    }

    public void setMosaicpath(String mosaicpath) {
        this.mosaicpath = mosaicpath == null ? null : mosaicpath.trim();
    }
}