package com.zeng.entity;

/**
 *
 * @author Administrator
 * @date 2018/3/17
 */
public class Msg {

    private String title;
    private String content;
    private String extraInfo;

    public Msg(String 测试标题, String 测试内容, String s){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
