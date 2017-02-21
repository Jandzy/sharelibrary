package com.jandzy.sharelibrary.share;

/**
 * Created by jrazy on 2017/2/20.
 */
public class ShareImgMedia implements IShareMedia {
    private String localImgUrl = "/storage/emulated/0/tencent/QQfile_recv/11.png";

    public String getLocalImgUrl() {
        return localImgUrl;
    }

    public void setLocalImgUrl(String localImgUrl) {
        this.localImgUrl = localImgUrl;
    }
}
