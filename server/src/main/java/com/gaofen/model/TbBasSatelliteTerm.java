package com.gaofen.model;

import java.util.Date;

public class TbBasSatelliteTerm {
    private Long dataId;

    private String satelliteId;

    private String sensorId;

    private Date sceneDate;

    private String productLevel;

    private String recstationId;

    private Float overallQuality;

    private Float scenePath;

    private Float scenerow;

    private String productName;

    private Integer state;

    private String productPath;

    private String fileSize;

    private String pictureFormat;

    private Float topLeftLatitude;

    private Float topLeftLongitude;

    private Float topRightLatitude;

    private Float topRightLongitude;

    private Float bottomRightLatitude;

    private Float bottomRightLongitude;

    private Float bottomLeftLatitude;

    private Float bottomLeftLongitude;

    private Date instorageDate;

    private String testss;

    private Integer myobjState;

    private Integer imgOperateState;

    private Integer sceneid;

    private String catalogId;

    private Date pushDate;

    private Integer productId;

    private String mosaicPath;

    private Integer coverageStatus;

    private Integer cloudPercent;

    private Integer imageGsd;

    private String bakPath;

    private Integer polygonState;

    private Integer producttype;

    private Integer auditState;

    private String auditDate;

    private String auditOperator;

    private String fOverallImageQuality;

    private String fKeyAreasCloum;

    private String fKeyAreasCloumCover;

    private String fImageIntegrity;

    private String fAreaName;

    public TbBasSatelliteTerm() {
        super();
    }

    public TbBasSatelliteTerm(Long dataId, String satelliteId, String sensorId, Date sceneDate, String productLevel, String recstationId, Float overallQuality, Float scenePath, Float scenerow, String productName, Integer state, String productPath, String fileSize, String pictureFormat, Float topLeftLatitude, Float topLeftLongitude, Float topRightLatitude, Float topRightLongitude, Float bottomRightLatitude, Float bottomRightLongitude, Float bottomLeftLatitude, Float bottomLeftLongitude, Date instorageDate, String testss, Integer myobjState, Integer imgOperateState, Integer sceneid, String catalogId, Date pushDate, Integer productId, String mosaicPath, Integer coverageStatus, Integer cloudPercent, Integer imageGsd, String bakPath, Integer polygonState, Integer productType, Integer auditState, String auditDate, String auditOperator, String fOverallImageQuality, String fKeyAreasCloum, String fKeyAreasCloumCover, String fImageIntegrity, String fAreaName) {
        this.dataId = dataId;
        this.satelliteId = satelliteId;
        this.sensorId = sensorId;
        this.sceneDate = sceneDate;
        this.productLevel = productLevel;
        this.recstationId = recstationId;
        this.overallQuality = overallQuality;
        this.scenePath = scenePath;
        this.scenerow = scenerow;
        this.productName = productName;
        this.state = state;
        this.productPath = productPath;
        this.fileSize = fileSize;
        this.pictureFormat = pictureFormat;
        this.topLeftLatitude = topLeftLatitude;
        this.topLeftLongitude = topLeftLongitude;
        this.topRightLatitude = topRightLatitude;
        this.topRightLongitude = topRightLongitude;
        this.bottomRightLatitude = bottomRightLatitude;
        this.bottomRightLongitude = bottomRightLongitude;
        this.bottomLeftLatitude = bottomLeftLatitude;
        this.bottomLeftLongitude = bottomLeftLongitude;
        this.instorageDate = instorageDate;
        this.testss = testss;
        this.myobjState = myobjState;
        this.imgOperateState = imgOperateState;
        this.sceneid = sceneid;
        this.catalogId = catalogId;
        this.pushDate = pushDate;
        this.productId = productId;
        this.mosaicPath = mosaicPath;
        this.coverageStatus = coverageStatus;
        this.cloudPercent = cloudPercent;
        this.imageGsd = imageGsd;
        this.bakPath = bakPath;
        this.polygonState = polygonState;
        this.producttype = producttype;
        this.auditState = auditState;
        this.auditDate = auditDate;
        this.auditOperator = auditOperator;
        this.fOverallImageQuality = fOverallImageQuality;
        this.fKeyAreasCloum = fKeyAreasCloum;
        this.fKeyAreasCloumCover = fKeyAreasCloumCover;
        this.fImageIntegrity = fImageIntegrity;
        this.fAreaName = fAreaName;
    }

    public Long getDataId() {
        return dataId;
    }

    public TbBasSatelliteTerm setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getSatelliteId() {
        return satelliteId;
    }

    public TbBasSatelliteTerm setSatelliteId(String satelliteId) {
        this.satelliteId = satelliteId;
        return this;
    }

    public String getSensorId() {
        return sensorId;
    }

    public TbBasSatelliteTerm setSensorId(String sensorId) {
        this.sensorId = sensorId;
        return this;
    }

    public Date getSceneDate() {
        return sceneDate;
    }

    public TbBasSatelliteTerm setSceneDate(Date sceneDate) {
        this.sceneDate = sceneDate;
        return this;
    }

    public String getProductLevel() {
        return productLevel;
    }

    public TbBasSatelliteTerm setProductLevel(String productLevel) {
        this.productLevel = productLevel;
        return this;
    }

    public String getRecstationId() {
        return recstationId;
    }

    public TbBasSatelliteTerm setRecstationId(String recstationId) {
        this.recstationId = recstationId;
        return this;
    }

    public Float getOverallQuality() {
        return overallQuality;
    }

    public TbBasSatelliteTerm setOverallQuality(Float overallQuality) {
        this.overallQuality = overallQuality;
        return this;
    }

    public Float getScenePath() {
        return scenePath;
    }

    public TbBasSatelliteTerm setScenePath(Float scenePath) {
        this.scenePath = scenePath;
        return this;
    }

    public Float getScenerow() {
        return scenerow;
    }

    public TbBasSatelliteTerm setScenerow(Float scenerow) {
        this.scenerow = scenerow;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public TbBasSatelliteTerm setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public TbBasSatelliteTerm setState(Integer state) {
        this.state = state;
        return this;
    }

    public String getProductPath() {
        return productPath;
    }

    public TbBasSatelliteTerm setProductPath(String productPath) {
        this.productPath = productPath;
        return this;
    }

    public String getFileSize() {
        return fileSize;
    }

    public TbBasSatelliteTerm setFileSize(String fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public String getPictureFormat() {
        return pictureFormat;
    }

    public TbBasSatelliteTerm setPictureFormat(String pictureFormat) {
        this.pictureFormat = pictureFormat;
        return this;
    }

    public Float getTopLeftLatitude() {
        return topLeftLatitude;
    }

    public TbBasSatelliteTerm setTopLeftLatitude(Float topLeftLatitude) {
        this.topLeftLatitude = topLeftLatitude;
        return this;
    }

    public Float getTopLeftLongitude() {
        return topLeftLongitude;
    }

    public TbBasSatelliteTerm setTopLeftLongitude(Float topLeftLongitude) {
        this.topLeftLongitude = topLeftLongitude;
        return this;
    }

    public Float getTopRightLatitude() {
        return topRightLatitude;
    }

    public TbBasSatelliteTerm setTopRightLatitude(Float topRightLatitude) {
        this.topRightLatitude = topRightLatitude;
        return this;
    }

    public Float getTopRightLongitude() {
        return topRightLongitude;
    }

    public TbBasSatelliteTerm setTopRightLongitude(Float topRightLongitude) {
        this.topRightLongitude = topRightLongitude;
        return this;
    }

    public Float getBottomRightLatitude() {
        return bottomRightLatitude;
    }

    public TbBasSatelliteTerm setBottomRightLatitude(Float bottomRightLatitude) {
        this.bottomRightLatitude = bottomRightLatitude;
        return this;
    }

    public Float getBottomRightLongitude() {
        return bottomRightLongitude;
    }

    public TbBasSatelliteTerm setBottomRightLongitude(Float bottomRightLongitude) {
        this.bottomRightLongitude = bottomRightLongitude;
        return this;
    }

    public Float getBottomLeftLatitude() {
        return bottomLeftLatitude;
    }

    public TbBasSatelliteTerm setBottomLeftLatitude(Float bottomLeftLatitude) {
        this.bottomLeftLatitude = bottomLeftLatitude;
        return this;
    }

    public Float getBottomLeftLongitude() {
        return bottomLeftLongitude;
    }

    public TbBasSatelliteTerm setBottomLeftLongitude(Float bottomLeftLongitude) {
        this.bottomLeftLongitude = bottomLeftLongitude;
        return this;
    }

    public Date getInstorageDate() {
        return instorageDate;
    }

    public TbBasSatelliteTerm setInstorageDate(Date instorageDate) {
        this.instorageDate = instorageDate;
        return this;
    }

    public String getTestss() {
        return testss;
    }

    public TbBasSatelliteTerm setTestss(String testss) {
        this.testss = testss;
        return this;
    }

    public Integer getMyobjState() {
        return myobjState;
    }

    public TbBasSatelliteTerm setMyobjState(Integer myobjState) {
        this.myobjState = myobjState;
        return this;
    }

    public Integer getImgOperateState() {
        return imgOperateState;
    }

    public TbBasSatelliteTerm setImgOperateState(Integer imgOperateState) {
        this.imgOperateState = imgOperateState;
        return this;
    }

    public Integer getSceneid() {
        return sceneid;
    }

    public TbBasSatelliteTerm setSceneid(Integer sceneid) {
        this.sceneid = sceneid;
        return this;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public TbBasSatelliteTerm setCatalogId(String catalogId) {
        this.catalogId = catalogId;
        return this;
    }

    public Date getPushDate() {
        return pushDate;
    }

    public TbBasSatelliteTerm setPushDate(Date pushDate) {
        this.pushDate = pushDate;
        return this;
    }

    public Integer getProductId() {
        return productId;
    }

    public TbBasSatelliteTerm setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public String getMosaicPath() {
        return mosaicPath;
    }

    public TbBasSatelliteTerm setMosaicPath(String mosaicPath) {
        this.mosaicPath = mosaicPath;
        return this;
    }

    public Integer getCoverageStatus() {
        return coverageStatus;
    }

    public TbBasSatelliteTerm setCoverageStatus(Integer coverageStatus) {
        this.coverageStatus = coverageStatus;
        return this;
    }

    public Integer getCloudPercent() {
        return cloudPercent;
    }

    public TbBasSatelliteTerm setCloudPercent(Integer cloudPercent) {
        this.cloudPercent = cloudPercent;
        return this;
    }

    public Integer getImageGsd() {
        return imageGsd;
    }

    public TbBasSatelliteTerm setImageGsd(Integer imageGsd) {
        this.imageGsd = imageGsd;
        return this;
    }

    public String getBakPath() {
        return bakPath;
    }

    public TbBasSatelliteTerm setBakPath(String bakPath) {
        this.bakPath = bakPath;
        return this;
    }

    public Integer getPolygonState() {
        return polygonState;
    }

    public TbBasSatelliteTerm setPolygonState(Integer polygonState) {
        this.polygonState = polygonState;
        return this;
    }



    public Integer getAuditState() {
        return auditState;
    }

    public TbBasSatelliteTerm setAuditState(Integer auditState) {
        this.auditState = auditState;
        return this;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public TbBasSatelliteTerm setAuditDate(String auditDate) {
        this.auditDate = auditDate;
        return this;
    }

    public String getAuditOperator() {
        return auditOperator;
    }

    public TbBasSatelliteTerm setAuditOperator(String auditOperator) {
        this.auditOperator = auditOperator;
        return this;
    }

    public String getfOverallImageQuality() {
        return fOverallImageQuality;
    }

    public TbBasSatelliteTerm setfOverallImageQuality(String fOverallImageQuality) {
        this.fOverallImageQuality = fOverallImageQuality;
        return this;
    }

    public String getfKeyAreasCloum() {
        return fKeyAreasCloum;
    }

    public TbBasSatelliteTerm setfKeyAreasCloum(String fKeyAreasCloum) {
        this.fKeyAreasCloum = fKeyAreasCloum;
        return this;
    }

    public String getfKeyAreasCloumCover() {
        return fKeyAreasCloumCover;
    }

    public TbBasSatelliteTerm setfKeyAreasCloumCover(String fKeyAreasCloumCover) {
        this.fKeyAreasCloumCover = fKeyAreasCloumCover;
        return this;
    }

    public String getfImageIntegrity() {
        return fImageIntegrity;
    }

    public TbBasSatelliteTerm setfImageIntegrity(String fImageIntegrity) {
        this.fImageIntegrity = fImageIntegrity;
        return this;
    }

    public String getfAreaName() {
        return fAreaName;
    }

    public TbBasSatelliteTerm setfAreaName(String fAreaName) {
        this.fAreaName = fAreaName;
        return this;
    }
}