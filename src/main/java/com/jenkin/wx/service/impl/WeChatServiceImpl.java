package com.jenkin.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jenkin.wx.dao.WeChatDao;
import com.jenkin.wx.pojo.AccessToken;
import com.jenkin.wx.pojo.message.*;
import com.jenkin.wx.service.WeChatService;
import com.jenkin.wx.util.DateUtil;
import com.jenkin.wx.util.WeChatMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: jenkinwang
 * @date: 2018/9/27 22:17
 * @description:
 */
@Service
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private WeChatDao weChatDao;

    @Override
    public int insertAccessToken(AccessToken accessToken) {
        return weChatDao.insertAccessToken(accessToken);
    }

    @Override
    public AccessToken queryLatestAccessToken() {
        return weChatDao.queryLatestAccessToken();
    }

    @Override
    public String handleWeChatMessage(HttpServletRequest request) {
        String response = "";

        JSONObject jsonObject = WeChatMessageUtil.xmlToJson(request);
        String ToUserName = jsonObject.get("ToUserName").toString();
        String FromUserName = jsonObject.get("FromUserName").toString();
        String MsgType = jsonObject.get("MsgType").toString();

        if (WeChatMessageUtil.MESSAGE_TEXT.equals(MsgType)) {

            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(FromUserName);
            textMessage.setFromUserName(ToUserName);
            textMessage.setMsgType(WeChatMessageUtil.MESSAGE_TEXT);
            textMessage.setCreateTime(DateUtil.getCurrentMillis()/1000);
            textMessage.setContent("收到了");

            response = WeChatMessageUtil.toXml(textMessage);
        } else if (WeChatMessageUtil.MESSAGE_IMAGE.equals(MsgType)) {

            ImageMessage imageMessage = new ImageMessage();
            Image image = new Image();
            image.setMediaId(jsonObject.get("MediaId").toString());
            imageMessage.setToUserName(FromUserName);
            imageMessage.setFromUserName(ToUserName);
            imageMessage.setMsgType(WeChatMessageUtil.MESSAGE_IMAGE);
            imageMessage.setCreateTime(DateUtil.getCurrentMillis()/1000);
            imageMessage.setImage(image);

            response = WeChatMessageUtil.toXml(imageMessage);
        } else if (WeChatMessageUtil.MESSAGE_VOICE.equals(MsgType)) {

            VoiceMessage voiceMessage = new VoiceMessage();
            Voice voice = new Voice();
            voice.setMediaId(jsonObject.get("MediaId").toString());
            voiceMessage.setToUserName(FromUserName);
            voiceMessage.setFromUserName(ToUserName);
            voiceMessage.setMsgType(WeChatMessageUtil.MESSAGE_VOICE);
            voiceMessage.setCreateTime(DateUtil.getCurrentMillis()/1000);
            voiceMessage.setVoice(voice);

            response = WeChatMessageUtil.toXml(voiceMessage);
        } else if (WeChatMessageUtil.MESSAGE_VIDEO.equals(MsgType)) {

            VideoMessage videoMessage = new VideoMessage();
            Video video = new Video();
            video.setTitle("");
            video.setDescription("");
            video.setMediaId(jsonObject.get("MediaId").toString());
            videoMessage.setToUserName(FromUserName);
            videoMessage.setFromUserName(ToUserName);
            videoMessage.setMsgType(WeChatMessageUtil.MESSAGE_VIDEO);
            videoMessage.setCreateTime(DateUtil.getCurrentMillis()/1000);
            videoMessage.setVideo(video);

            response = WeChatMessageUtil.toXml(videoMessage);
        } else if (WeChatMessageUtil.MESSAGE_EVENT.equals(MsgType)) {
            String Event  = jsonObject.get("Event").toString();

            if (WeChatMessageUtil.MESSAGE_SUBSCRIBE_EVENT.equals(Event)) {

                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(FromUserName);
                textMessage.setFromUserName(ToUserName);
                textMessage.setCreateTime(DateUtil.getCurrentMillis()/1000);
                textMessage.setMsgType(WeChatMessageUtil.MESSAGE_TEXT);
                textMessage.setContent("亲，您终于来了!感谢您的关注!!!");

                response = WeChatMessageUtil.toXml(textMessage);
            } else if (WeChatMessageUtil.MESSAGE_SCAN_EVENT.equals(Event)) {

                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(FromUserName);
                textMessage.setFromUserName(ToUserName);
                textMessage.setMsgType(WeChatMessageUtil.MESSAGE_TEXT);
                textMessage.setCreateTime(DateUtil.getCurrentMillis()/1000);
                textMessage.setContent("不要老是扫人家嘛，人家会害羞的。🤗");

                response = WeChatMessageUtil.toXml(textMessage);
            } else if (WeChatMessageUtil.MESSAGE_LOCATION_EVENT.equals(Event)) {
                String Longitude = jsonObject.get("Longitude").toString();
                String Latitude = jsonObject.get("Latitude").toString();
                String Precision = jsonObject.get("Precision").toString();

                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(FromUserName);
                textMessage.setFromUserName(ToUserName);
                textMessage.setMsgType(WeChatMessageUtil.MESSAGE_TEXT);
                textMessage.setCreateTime(DateUtil.getCurrentMillis()/1000);
                StringBuffer sb = new StringBuffer();
                sb.append("您的地理位置：经度为 ").append(Longitude)
                        .append(",纬度为 ").append(Latitude)
                        .append(",精确度为 ").append(Precision).append("米。");
                textMessage.setContent(sb.toString());

                response = WeChatMessageUtil.toXml(textMessage);
            } else if (WeChatMessageUtil.MESSAGE_CLICK_EVENT.equals(Event)) {


            } else if (WeChatMessageUtil.MESSAGE_VIEW_EVENT.equals(Event)) {


            }
        }

        return response;
    }
}
