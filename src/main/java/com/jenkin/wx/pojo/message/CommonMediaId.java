package com.jenkin.wx.pojo.message;

/**
 * @author: jenkinwang
 * @date: 2018/9/29 17:11
 * @description:
 */
public class CommonMediaId {
    private String MediaId; // 通过素材管理中的接口上传多媒体文件，得到的id

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
