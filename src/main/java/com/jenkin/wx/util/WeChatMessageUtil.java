package com.jenkin.wx.util;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.XML;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: jenkinwang
 * @date: 2018/9/29 11:09
 * @description: 处理微信消息的工具类
 */
public class WeChatMessageUtil {
    /*
        文本消息
     */
    public static final String MESSAGE_TEXT = "text";

    /*
        图片消息
     */
    public static final String MESSAGE_IMAGE = "image";

    /*
        语音消息
     */
    public static final String MESSAGE_VOICE = "voice";

    /*
        视频消息
     */
    public static final String MESSAGE_VIDEO = "video";

    /*
        小视频消息
     */
    public static final String MESSAGE_SHORT_VIDEO = "shortvideo";

    /*
        地理位置消息
     */
    public static final String MESSAGE_LOCATION = "location";

    /*
        链接消息
     */
    public static final String MESSAGE_LINK = "link";

    /*
        事件消息
     */
    public static final String MESSAGE_EVENT = "event";

    /*
        关注事件(用户未关注时，进行关注后的时间推送)
     */
    public static final String MESSAGE_SUBSCRIBE_EVENT = "subscribe";

    /*
        取消关注事件
     */
    public static final String MESSAGE_UNSUBSCRIBE_EVENT = "unsubscribe";

    /*
        用户已关注时的时间推送
     */
    public static final String MESSAGE_SCAN_EVENT = "SCAN";

    /*
        上报地理位置事件
     */
    public static final String MESSAGE_LOCATION_EVENT = "LOCATION";

    /*
        点击菜单时拉取消息的事件推送
     */
    public static final String MESSAGE_CLICK_EVENT = "CLICK";

    /*
        点击菜单跳转链接时的事件推送
     */
    public static final String MESSAGE_VIEW_EVENT = "VIEW";

    /**
     * 将xml转换成json
     * @param request
     * @return
     */
    public static JSONObject xmlToJson(HttpServletRequest request) {
        InputStream inputStream = null;
        JSONObject json = null;
        try {
            inputStream = request.getInputStream();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            Map<String, String> map = new HashMap<>();
            for (Element element : elements) {
                map.put(element.getName(), element.getText());
            }

            json = (JSONObject) JSONObject.toJSON(map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        System.out.println("***************************************");
        System.out.println("收到的xml格式：\n" + toXml(json));
        System.out.println("***************************************");
        return json;
    }

    /**
     * 将json转换成xml
     * @param json
     * @return
     */
    public static String jsonToXml(String json) {
        org.json.JSONObject jsonObject = new org.json.JSONObject(json);
        return "<mxl>" + XML.toString(jsonObject) + "</xml>";
    }

    /**
     * 转换为xml格式
     * @param message
     * @return
     */
    public static String toXml(Object message) {
        XStream xStream = new XStream();
        xStream.alias("xml", message.getClass());
        return xStream.toXML(message);
    }
}
