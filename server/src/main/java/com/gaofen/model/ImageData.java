package com.gaofen.model;

import java.util.Date;

public class ImageData {

    private long dataId;

    private Date sceneDate;

    private long cloudPercent;

    private String sensorId;

    private String satelliteId;

    private String describe;

    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public long getCloudPercent() {
        return cloudPercent;
    }

    public void setCloudPercent(long cloudPercent) {
        this.cloudPercent = cloudPercent;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getSatelliteId() {
        return satelliteId;
    }

    public void setSatelliteId(String satelliteId) {
        this.satelliteId = satelliteId;
    }

    public Date getSceneDate() {
        return sceneDate;
    }

    public void setSceneDate(Date sceneDate) {
        this.sceneDate = sceneDate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "ImageData{" +
                "dataId=" + dataId +
                ", sceneDate=" + sceneDate +
                ", cloudPercent=" + cloudPercent +
                ", sensorId='" + sensorId + '\'' +
                ", satelliteId='" + satelliteId + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}