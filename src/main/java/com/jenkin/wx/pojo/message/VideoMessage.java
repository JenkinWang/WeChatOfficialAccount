package com.jenkin.wx.pojo.message;

/**
 * @author: jenkinwang
 * @date: 2018/9/29 17:26
 * @description:
 */
public class VideoMessage extends CommonMessage {
    private String ThumbMediaId;    // 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
    private Video Video;

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    public com.jenkin.wx.pojo.message.Video getVideo() {
        return Video;
    }

    public void setVideo(com.jenkin.wx.pojo.message.Video video) {
        Video = video;
    }
}
