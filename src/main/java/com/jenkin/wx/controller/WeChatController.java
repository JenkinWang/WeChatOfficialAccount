package com.jenkin.wx.controller;

import com.alibaba.fastjson.JSONObject;
import com.jenkin.wx.pojo.AccessToken;
import com.jenkin.wx.service.WeChatService;
import com.jenkin.wx.util.CommonUtil;
import com.jenkin.wx.util.DateUtil;
import com.jenkin.wx.util.WeChatMessageUtil;
import com.jenkin.wx.util.WeChatVerificationUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author: jenkinwang
 * @date: 2018/9/25 23:18
 * @description:
 */
@Controller
public class WeChatController {
    @Autowired
    private WeChatService weChatService;

    /**
     * 验证消息来自于服务器
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @param request
     * @param response
     */
    @RequestMapping(value = "/verificate", method = RequestMethod.GET)
    public void weChatVerification(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr,
            HttpServletRequest request, HttpServletResponse response) {

        try {
            String _signature = WeChatVerificationUtil.verificateWX(timestamp, nonce);
            System.out.println(signature);
            System.out.println(_signature);

            PrintWriter printWriter = response.getWriter();
            // 对比signature
            if (signature.equals(_signature)) {
                System.out.println("success");
                printWriter.print(echostr);
            } else {
                System.out.println("fail");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 手动获取access_token值
     * @return
     * @throws IOException
     */
    public String queryLatestAccessToken() throws IOException {
        AccessToken accessToken = weChatService.queryLatestAccessToken();

        long curMillis = DateUtil.getCurrentMillis();
        long myCustomMillis = DateUtil.getCustomDateMillis(accessToken.getCreate_time());
        if ((curMillis - myCustomMillis) / 1000 >= 7200) {   // 如果大于7200秒，则access_token过期，需要重新获取access_token值
            System.out.println("access_token值已过期，重新获取并入库......");
            // 重新获取access_token值 并插入数据库
            accessToken = CommonUtil.getAccessToken();
            weChatService.insertAccessToken(accessToken);
        }

        return accessToken.getAccess_token();
    }

    /**
     * 创建菜单
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/createMenu", method = RequestMethod.GET)
    public void createMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String menu = JSONObject.toJSONString(MenuManagerController.getMenu());

        System.out.println(menu);
        String accessToken = queryLatestAccessToken();
        int msgCode = CommonUtil.createMenu(accessToken, menu);
        PrintWriter printWriter = response.getWriter();
        if (msgCode == 0) {

            printWriter.write("Create Menu successfully......");
            System.out.println("成功创建菜单......");
        } else {
            printWriter.write("Create Menu fail......");
            System.out.println("创建菜单失败......");
        }
    }

    /**
     * 创建个性化菜单
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/createIndividualMenu", method = RequestMethod.GET)
    public void createIndividualMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("Coming......");
        String accessToken = queryLatestAccessToken();

        String menu = JSONObject.toJSONString(MenuManagerController.getMenu());
        int msgCode = CommonUtil.createIndividualMenu(accessToken, menu);
        PrintWriter printWriter = response.getWriter();
        if (msgCode == 0) {

            printWriter.write("Create Individual Menu successfully......");
            System.out.println("成功创建个性化菜单......");
        } else {
            printWriter.write("Create Individual Menu fail......");
            System.out.println("创建个性化菜单失败......");
        }
    }

    /**
     * 删除菜单
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/deleteMenu", method = RequestMethod.GET)
    public void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String accessToken = queryLatestAccessToken();
        int msgCode = CommonUtil.deleteMenu(accessToken);
        PrintWriter printWriter = response.getWriter();
        if (msgCode == 0) {

            printWriter.write("Delete Menu successfully......");
            System.out.println("成功删除菜单......");
        } else {
            printWriter.write("Delete Menu fail......");
            System.out.println("删除菜单失败......");
        }
    }

    /**
     * 删除个性化菜单
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/deleteIndividualMenu", method = RequestMethod.GET)
    public void deleteIndividualMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String menuid = "449712958";     // menuid 通过查询个性化菜单查询
        String accessToken = queryLatestAccessToken();
        int msgCode = CommonUtil.deleteIndividualMenu(accessToken, JSONObject.toJSONString("{'menuid':" + menuid + "}"));
        PrintWriter printWriter = response.getWriter();
        if (msgCode == 0) {

            printWriter.write("Delete Individual Menu successfully......");
            System.out.println("成功删除个性化菜单......");
        } else {
            printWriter.write("Delete Individual Menu fail......");
            System.out.println("删除个性化菜单失败......");
        }
    }

    /**
     *  接收普通消息
     */
    @RequestMapping(value = "/verificate", method = RequestMethod.POST)
    public void receiveTextMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 处理消息
        String resp = weChatService.handleWeChatMessage(request);

        PrintWriter printWriter = response.getWriter();
        System.out.println("***************************************");
        System.out.println("返回的xml格式：\n" + resp);
        System.out.println("***************************************");
        printWriter.write(resp);
        printWriter.flush();
        printWriter.close();

    }
}
