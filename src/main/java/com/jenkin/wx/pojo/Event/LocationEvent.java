package com.jenkin.wx.pojo.Event;

/**
 * @author: jenkinwang
 * @date: 2018/9/30 15:18
 * @description:
 */
public class LocationEvent extends CommonEvent {
    private String Latitude;    // 地理位置纬度
    private String Longitude;   // 地理位置经度
    private String Precision;   // 地理位置精度

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPrecision() {
        return Precision;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }
}
