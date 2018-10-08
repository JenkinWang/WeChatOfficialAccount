package com.jenkin.wx.pojo.message;

/**
 * @author: jenkinwang
 * @date: 2018/9/29 17:28
 * @description:
 */
public class Video extends CommonMediaId {
    private String Title;   // 视频消息的标题
    private String Description; // 视频消息的描述

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
