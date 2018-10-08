package com.jenkin.wx.pojo.message;

/**
 * @author: jenkinwang
 * @date: 2018/9/29 11:01
 * @description: 文本消息
 */
public class TextMessage extends CommonMessage {
    private String Content;     // 文本消息内容

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
