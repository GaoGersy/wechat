package com.gaofen.model;

import java.util.Date;
import java.util.List;

public class SearchConditionBean {
    private List<Long> dataIds;
    private Date startTime;
    private Date endTime;
    private Long cloudPercent;
    private Float topLeftLatitude;
    private Float topLeftLongitude;
    private Float topRightLatitude;
    private Float topRightLongitude;
    private Float bottomRightLatitude;
    private Float bottomRightLongitude;
    private Float bottomLeftLatitude;
    private Float bottomLeftLongitude;
    private Boolean gf1_enable;
    private Boolean gf2_enable;
    private Boolean gf3_enable;
    private Boolean gf4_enable;
    private Boolean isArea;//是否是通过地区搜索

    private List<String> sensorIds_1;
    private List<String> sensorIds_2;

    private List<String> satelliteIds;//用户选中的卫星列表

    private String satelliteSql;
    private String codeSql;//行政区编码的卫星sql

    private Boolean isAllProvince;
    private Boolean isAllCity;

    private String code ;

    private int currentPage=1;
    private int pageSize;
    private long start;
    private long end;


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getCloudPercent() {
        return cloudPercent;
    }

    public void setCloudPercent(long cloudPercent) {
        this.cloudPercent = cloudPercent;
    }

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

    public boolean isGf1_enable() {
        return gf1_enable;
    }

    public void setGf1_enable(boolean gf1_enable) {
        this.gf1_enable = gf1_enable;
    }

    public boolean isGf2_enable() {
        return gf2_enable;
    }

    public void setGf2_enable(boolean gf2_enable) {
        this.gf2_enable = gf2_enable;
    }

    public boolean isGf3_enable() {
        return gf3_enable;
    }

    public void setGf3_enable(boolean gf3_enable) {
        this.gf3_enable = gf3_enable;
    }

    public boolean isGf4_enable() {
        return gf4_enable;
    }

    public void setGf4_enable(boolean gf4_enable) {
        this.gf4_enable = gf4_enable;
    }

    public List<String> getSensorIds_1() {
        return sensorIds_1;
    }

    public void setSensorIds_1(List<String> sensorIds_1) {
        this.sensorIds_1 = sensorIds_1;
    }

    public List<String> getSensorIds_2() {
        return sensorIds_2;
    }

    public void setSensorIds_2(List<String> sensorIds_2) {
        this.sensorIds_2 = sensorIds_2;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getStart() {
        if (start==0) {
            start = pageSize * (currentPage - 1) + 1;
        }
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        if (end==0){
            end = pageSize*currentPage;
        }
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isArea() {
        return isArea;
    }

    public void setArea(boolean area) {
        isArea = area;
    }

    public Boolean isAllProvince() {
        return isAllProvince;
    }

    public void setAllProvince(Boolean allProvince) {
        isAllProvince = allProvince;
    }

    public Boolean isAllCity() {
        return isAllCity;
    }

    public void setAllCity(Boolean allCity) {
        isAllCity = allCity;
    }

    public List<Long> getDataIds() {
        return dataIds;
    }

    public void setDataIds(List<Long> dataIds) {
        this.dataIds = dataIds;
    }

    public List<String> getSatelliteIds() {
        return satelliteIds;
    }

    public void setSatelliteIds(List<String> satelliteIds) {
        this.satelliteIds = satelliteIds;
    }

    public String getSatelliteSql() {
        return satelliteSql;
    }

    public void setSatelliteSql(String satelliteSql) {
        this.satelliteSql = satelliteSql;
    }

    public String getCodeSql() {
        return codeSql;
    }

    public void setCodeSql(String codeSql) {
        this.codeSql = codeSql;
    }

    @Override
    public String toString() {
        return "SearchConditionBean{" +
                "dataIds=" + dataIds +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", cloudPercent=" + cloudPercent +
                ", topLeftLatitude=" + topLeftLatitude +
                ", topLeftLongitude=" + topLeftLongitude +
                ", topRightLatitude=" + topRightLatitude +
                ", topRightLongitude=" + topRightLongitude +
                ", bottomRightLatitude=" + bottomRightLatitude +
                ", bottomRightLongitude=" + bottomRightLongitude +
                ", bottomLeftLatitude=" + bottomLeftLatitude +
                ", bottomLeftLongitude=" + bottomLeftLongitude +
                ", gf1_enable=" + gf1_enable +
                ", gf2_enable=" + gf2_enable +
                ", gf3_enable=" + gf3_enable +
                ", gf4_enable=" + gf4_enable +
                ", isArea=" + isArea +
                ", sensorIds_1=" + sensorIds_1 +
                ", sensorIds_2=" + sensorIds_2 +
                ", satelliteIds=" + satelliteIds +
                ", satelliteSql='" + satelliteSql + '\'' +
                ", isAllProvince=" + isAllProvince +
                ", isAllCity=" + isAllCity +
                ", code='" + code + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
