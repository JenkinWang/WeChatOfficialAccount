package com.jenkin.wx.pojo.Event;

/**
 * @author: jenkinwang
 * @date: 2018/9/30 15:40
 * @description:
 */
public class ViewEvent extends CommonEvent {
    private String EventKey;    // 事件KEY值，设置的跳转URL

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
