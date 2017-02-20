package com.jandzy.sharelibrary.share;

/**
 * 分享文字
 */
public class ShareTextMedia implements IShareMedia {

    private String text = "";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
