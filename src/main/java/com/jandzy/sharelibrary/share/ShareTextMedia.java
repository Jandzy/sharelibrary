package com.jandzy.sharelibrary.share;

/**
 * Created by jrazy on 2017/2/20.
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
