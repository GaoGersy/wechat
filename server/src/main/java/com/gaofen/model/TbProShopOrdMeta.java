package com.gaofen.model;

import java.util.Date;

public class TbProShopOrdMeta {
    private long id;

    private long orderId;

    private long productId;

    private long state;

    private String satelliteId;

    private String sensorId;

    private Date productDate;

    private String productSize;

    private String filePath;

    private String reason;

    private String backPath;

    private Date backTime;

    private long sceneId;

    private Date downDate;

    private long pId;

    private String productName;

    private String classify;

    public TbProShopOrdMeta(long id, long orderId, long productId, long state, String satelliteId, String sensorId, Date productDate, String productSize, String filePath, String reason, String backPath, Date backTime, long sceneId, Date downDate, long pId, String productName, String classify) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.state = state;
        this.satelliteId = satelliteId;
        this.sensorId = sensorId;
        this.productDate = productDate;
        this.productSize = productSize;
        this.filePath = filePath;
        this.reason = reason;
        this.backPath = backPath;
        this.backTime = backTime;
        this.sceneId = sceneId;
        this.downDate = downDate;
        this.pId = pId;
        this.productName = productName;
        this.classify = classify;
    }

    public TbProShopOrdMeta() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getorderId() {
        return orderId;
    }

    public void setorderId(long orderId) {
        this.orderId = orderId;
    }

    public long getproductId() {
        return productId;
    }

    public void setproductId(long productId) {
        this.productId = productId;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public String getsatelliteId() {
        return satelliteId;
    }

    public void setsatelliteId(String satelliteId) {
        this.satelliteId = satelliteId == null ? null : satelliteId.trim();
    }

    public String getsensorId() {
        return sensorId;
    }

    public void setsensorId(String sensorId) {
        this.sensorId = sensorId == null ? null : sensorId.trim();
    }

    public Date getproductDate() {
        return productDate;
    }

    public void setproductDate(Date productDate) {
        this.productDate = productDate;
    }

    public String getproductSize() {
        return productSize;
    }

    public void setproductSize(String productSize) {
        this.productSize = productSize == null ? null : productSize.trim();
    }

    public String getfilePath() {
        return filePath;
    }

    public void setfilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getbackPath() {
        return backPath;
    }

    public void setbackPath(String backPath) {
        this.backPath = backPath == null ? null : backPath.trim();
    }

    public Date getbackTime() {
        return backTime;
    }

    public void setbackTime(Date backTime) {
        this.backTime = backTime;
    }

    public long getsceneId() {
        return sceneId;
    }

    public void setsceneId(long sceneId) {
        this.sceneId = sceneId;
    }

    public Date getdownDate() {
        return downDate;
    }

    public void setdownDate(Date downDate) {
        this.downDate = downDate;
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public String getproductName() {
        return productName;
    }

    public void setproductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify == null ? null : classify.trim();
    }
}