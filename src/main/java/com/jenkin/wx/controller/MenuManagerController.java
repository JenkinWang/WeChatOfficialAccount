package com.jenkin.wx.controller;

import com.jenkin.wx.pojo.button.*;
import org.springframework.stereotype.Controller;

/**
 * @author: jenkinwang
 * @date: 2018/9/28 14:28
 * @description:
 */
@Controller
public class MenuManagerController {

    /**
     *  管理菜单
     */
    public static Menu getMenu() {

        CommonButton btn1 = new CommonButton();
        btn1.setType("view");
        btn1.setName("GitHub主页");
        btn1.setUrl("https://github.com/JenkinWang");

        CommonButton btn2 = new CommonButton();
        btn2.setType("click");
        btn2.setName("打赏");
        btn2.setKey("reward");


        CommonButton cb1 = new CommonButton();
        cb1.setType("view");
        cb1.setName("百度一下");
        cb1.setUrl("http://www.baidu.com/");

        CommonButton cb2 = new CommonButton();
        cb2.setType("click");
        cb2.setName("今日天气");
        cb2.setUrl("http://www.baidu.com");
        cb2.setKey("weather");

        CommonButton cb3 = new CommonButton();
        cb3.setType("click");
        cb3.setName("我的位置");
        cb3.setUrl("http://www.baidu.com");
        cb3.setKey("location");

        // 个性菜单
        Matchrule matchrule = new Matchrule();
        matchrule.setTag_id("94");
        matchrule.setSex("1");
        matchrule.setCountry("China");
        matchrule.setProvince("Anhui");
        matchrule.setCity("Hefei");
        matchrule.setClient_platform_type("2");
        matchrule.setLanguage("zh_CN");

        // 设置三个主按钮
        CommonButton oneMain = new CommonButton();
        oneMain.setName("学习笔记");
        oneMain.setType("click");
        oneMain.setUrl("https://www.baidu.com");
        oneMain.setKey("/");

        ComplexButton twoMain = new ComplexButton();
        twoMain.setName("日常工具");
        twoMain.setSub_button(new CommonButton[]{cb1, cb2, cb3});

        ComplexButton threeMain = new ComplexButton();
        threeMain.setName("个人中心");
        threeMain.setSub_button(new CommonButton[]{btn1, btn2});

        // 封装
        Menu menu = new Menu();
        menu.setButton(new Button[]{oneMain, twoMain, threeMain});
        menu.setMatchrule(matchrule);

        return menu;

    }

}
