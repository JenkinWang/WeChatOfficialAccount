package com.jenkin.wx.pojo.button;

/**
 * @author: jenkinwang
 * @date: 2018/9/28 14:25
 * @description:
 */
public class CommonButton extends Button{
    private String type;
    private String key;
    private String url;
    private String appid;
    private String pagepath;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }
}
