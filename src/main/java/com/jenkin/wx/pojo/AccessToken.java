package com.jenkin.wx.pojo;

/**
 * @author: jenkinwang
 * @date: 2018/9/27 09:51
 * @description:
 */
public class AccessToken {
    private String access_token;
    private String expires_in;
    private String create_time;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
