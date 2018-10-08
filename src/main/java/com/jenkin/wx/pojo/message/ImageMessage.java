package com.jenkin.wx.pojo.message;

/**
 * @author: jenkinwang
 * @date: 2018/9/29 16:14
 * @description:
 */
public class ImageMessage extends CommonMessage {
    private String PicUrl;  // 图片链接（由系统生成）
    private Image Image;


    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public com.jenkin.wx.pojo.message.Image getImage() {
        return Image;
    }

    public void setImage(com.jenkin.wx.pojo.message.Image image) {
        Image = image;
    }
}
