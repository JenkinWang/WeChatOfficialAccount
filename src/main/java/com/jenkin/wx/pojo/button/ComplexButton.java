package com.jenkin.wx.pojo.button;

/**
 * @author: jenkinwang
 * @date: 2018/9/28 14:26
 * @description:
 */
public class ComplexButton extends Button {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
