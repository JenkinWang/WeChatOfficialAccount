package com.jenkin.wx.pojo.Event;

/**
 * @author: jenkinwang
 * @date: 2018/9/30 14:30
 * @description:
 */
public class SubscribeEvent extends CommonEvent {
    private String EventKey;    // 事件KEY值，qrscene_为前缀，后面为二维码的参数值
    private String Ticket;      // 二维码的ticket，可用来换取二维码图片

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }
}
