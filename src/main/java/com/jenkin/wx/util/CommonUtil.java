package com.jenkin.wx.util;

import com.alibaba.fastjson.JSONObject;
import com.jenkin.wx.pojo.AccessToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author: jenkinwang
 * @date: 2018/9/27 09:58
 * @description:
 */
public class CommonUtil {
    // 你账号的APPID
    private static final String APPID = "Your APPID";

    // 你账号的APPSECRET
    private static final String APPSECRET = "Your APPSECRET";

    // 获取access_token的URL
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;

    // 创建菜单URL
    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    // 删除菜单URL
    private static final String DELETE_MENU_RUL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    // 创建个性化菜单
    private static final String CREATE_INDIVIDUAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";

    // 删除个性化菜单
    private static final String DELETE_INDIVIDUAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN";

    /**
     * GET、POST请求
     * @param url
     * @param method
     * @return
     * @throws IOException
     */
    public static JSONObject httpRequest(String url, String method, String postData) throws IOException {
        JSONObject json = null;
        CloseableHttpClient client = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        // GET 请求
        if ("GET".equals(method)) {
            HttpGet request = new HttpGet(url);
            try {
                response = client.execute(request);
            } catch (IOException e) {
                e.printStackTrace();
            }

        // POST请求
        } else if ("POST".equals(method)) {
            HttpPost request = new HttpPost(url);
            try {
                StringEntity stringEntity = new StringEntity(postData, Charset.forName("UTF-8"));
                request.addHeader("Content-type", "application/json;charset=utf-8");
                request.setHeader("Accept", "application/json");
                request.setEntity(stringEntity);
                response = client.execute(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String result = EntityUtils.toString(response.getEntity());
        json = JSONObject.parseObject(result);
        return json;
    }

    /**
     * 创建菜单
     * @return
     */
    public static int createMenu(String accessToken, String menu) throws IOException {
        int result = 0;
        String createUrl = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject json = httpRequest(createUrl, "POST", menu);
        if (json != null) {
            if (0 != json.getInteger("errcode")) {
                result = json.getInteger("errcode");
                System.out.println("创建菜单失败 errcode:" + json.get("errcode").toString() + ", errmsg:" + json.get("errmsg").toString());
            }
        }
        return result;
    }

    /**
     * 删除菜单，包括个性化菜单
     * @param accessToken
     * @return
     */
    public static int deleteMenu(String accessToken) throws IOException {
        int result = 0;
        String deleteUrl = DELETE_MENU_RUL.replace("ACCESS_TOKEN", accessToken);
        JSONObject json = httpRequest(deleteUrl, "GET", null);
        if (json != null) {
            if (0 != json.getInteger("errcode")) {
                System.out.println("删除菜单失败 errcode:" + json.get("errcode").toString());
            }
        }
        return result;
    }

    /**
     * 创建个性化菜单
     * @param accessToken
     * @param menu
     * @return
     */
    public static int createIndividualMenu(String accessToken, String menu) throws IOException {
        int msgCode = -1;
        String createIndividualMenuUrl = CREATE_INDIVIDUAL_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject json = httpRequest(createIndividualMenuUrl, "POST", menu);
        if (json != null) {
            if (json.toString().contains("menuid")) {
                msgCode = 0;
                System.out.println("创建个性化菜单成功，menuid:" + json.get("menuid").toString());
            } else {
                System.out.println("创建个性化菜单失败，errcode:" + json.get("errcode").toString());
            }
        }
        return msgCode;
    }

    /**
     * 删除个性化菜单
     * @return
     */
    public static int deleteIndividualMenu(String accessToken, String menuid) throws IOException {
        int msgCode = 0;
        String deleteUrl = DELETE_INDIVIDUAL_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject json = httpRequest(deleteUrl, "POST", menuid);
        if (json != null) {
            if (0 != json.getInteger("errcode")) {
                System.out.println("删除个性化菜单失败 errcode:" + json.get("errcode").toString());
            }
        }
        return msgCode;
    }

    /**
     * 获取access_token
     * @return
     * @throws IOException
     */
    public static AccessToken getAccessToken() throws IOException {
        JSONObject json = httpRequest(ACCESS_TOKEN_URL, "GET", null);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccess_token(json.get("access_token").toString());
        accessToken.setExpires_in(json.get("expires_in").toString());
        return accessToken;
    }

}
