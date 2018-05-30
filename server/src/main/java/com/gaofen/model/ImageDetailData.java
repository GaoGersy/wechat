package com.gaofen.model;

public class ImageDetailData extends ImageData {
    private Float topLeftLatitude;

    private Float topLeftLongitude;

    private Float topRightLatitude;

    private Float topRightLongitude;

    private Float bottomRightLatitude;

    private Float bottomRightLongitude;

    private Float bottomLeftLatitude;

    private Float bottomLeftLongitude;

    private Integer productId;

    private String sceneId;

    private Integer productType;

    public Float getTopLeftLatitude() {
        return topLeftLatitude;
    }

    public void setTopLeftLatitude(Float topLeftLatitude) {
        this.topLeftLatitude = topLeftLatitude;
    }

    public Float getTopLeftLongitude() {
        return topLeftLongitude;
    }

    public void setTopLeftLongitude(Float topLeftLongitude) {
        this.topLeftLongitude = topLeftLongitude;
    }

    public Float getTopRightLatitude() {
        return topRightLatitude;
    }

    public void setTopRightLatitude(Float topRightLatitude) {
        this.topRightLatitude = topRightLatitude;
    }

    public Float getTopRightLongitude() {
        return topRightLongitude;
    }

    public void setTopRightLongitude(Float topRightLongitude) {
        this.topRightLongitude = topRightLongitude;
    }

    public Float getBottomRightLatitude() {
        return bottomRightLatitude;
    }

    public void setBottomRightLatitude(Float bottomRightLatitude) {
        this.bottomRightLatitude = bottomRightLatitude;
    }

    public Float getBottomRightLongitude() {
        return bottomRightLongitude;
    }

    public void setBottomRightLongitude(Float bottomRightLongitude) {
        this.bottomRightLongitude = bottomRightLongitude;
    }

    public Float getBottomLeftLatitude() {
        return bottomLeftLatitude;
    }

    public void setBottomLeftLatitude(Float bottomLeftLatitude) {
        this.bottomLeftLatitude = bottomLeftLatitude;
    }

    public Float getBottomLeftLongitude() {
        return bottomLeftLongitude;
    }

    public void setBottomLeftLongitude(Float bottomLeftLongitude) {
        this.bottomLeftLongitude = bottomLeftLongitude;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }
}
