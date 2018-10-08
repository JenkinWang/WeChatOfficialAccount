package com.jenkin.wx.service;

import com.jenkin.wx.pojo.AccessToken;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: jenkinwang
 * @date: 2018/9/27 22:17
 * @description:
 */
public interface WeChatService {

    int insertAccessToken(AccessToken accessToken);

    AccessToken queryLatestAccessToken();

    String handleWeChatMessage(HttpServletRequest request);
}
