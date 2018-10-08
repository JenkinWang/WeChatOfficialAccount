package com.jenkin.wx.pojo.Event;

/**
 * @author: jenkinwang
 * @date: 2018/9/30 15:39
 * @description:
 */
public class ClickEvent extends CommonEvent {
    private String EventKey;    // 事件KEY值，与自定义菜单接口中KEY值对应

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
