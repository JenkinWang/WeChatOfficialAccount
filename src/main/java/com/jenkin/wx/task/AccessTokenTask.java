package com.jenkin.wx.task;

import com.jenkin.wx.pojo.AccessToken;
import com.jenkin.wx.service.WeChatService;
import com.jenkin.wx.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: jenkinwang
 * @date: 2018/9/26 23:29
 * @description:
 */
@Controller
@Component("task")
public class AccessTokenTask {

    @Autowired
    private WeChatService weChatService;

    /**
     * 每两小时获取一次access_token值,并将access_token值存入数据库中
     */
    @Scheduled(cron = "0 0 */2 * * ?")
    public void getAccessToken() throws IOException {
        System.out.println("获取access_token值：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        AccessToken accessToken = CommonUtil.getAccessToken();
        System.out.println(accessToken.getAccess_token());
        System.out.println(accessToken.getExpires_in());

        // 将access_token存入数据库
        weChatService.insertAccessToken(accessToken);

    }
}
