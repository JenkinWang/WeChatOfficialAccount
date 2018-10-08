package com.jenkin.wx.pojo.message;

/**
 * @author: jenkinwang
 * @date: 2018/9/29 17:08
 * @description:
 */
public class VoiceMessage extends CommonMessage {
    private String Format;  // 语音格式，如amr，speex等
    private String Recognition; // 语音识别结果，UTF8编码
    private Voice Voice;

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        this.Voice = voice;
    }

    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }
}
