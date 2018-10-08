package com.jenkin.wx.pojo.message;

/**
 * @author: jenkinwang
 * @date: 2018/9/29 10:57
 * @description:
 */
public class CommonMessage {
    private String ToUserName;      // 开发者微信号
    private String FromUserName;    // 发送方帐号（一个OpenID）
    private Long CreateTime;        // 消息创建时间 （整型）
    private String MsgType;
    private Long MsgId;             // 消息id，64位整型

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public void setMsgId(Long msgId) {
        MsgId = msgId;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
