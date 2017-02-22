package com.jandzy.sharelibrary.sharecontent;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class ShareContentMedia {

    private String title = "分享标题";

    private String summary = "概述";

    private String targetUrl = "http://www.jandzy.com";

    //可以是网络图片url也可以是手机本地图片的url
    private String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487611614204&di=5a257bb0e70762132ef1c0e25804829e&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253D580%2Fsign%3Dd750e91db9014a90813e46b599763971%2F3ca7811c8701a18b4e9144e09e2f07082938fe9f.jpg";

    //只能是手机本地图片的url
    private String localImgUrl = "/storage/emulated/0/tencent/QQfile_recv/11.png";

    private String audioUrl = "";

    private String localAudioUrl = "";

    //可以包含本地图片url和网络图片url两种
    private ArrayList<String> imgUrls = new ArrayList<>();


    protected ShareContentMedia(String title, String summary, String targetUrl, String imageUrl,
                                String localImgUrl,String audioUrl,String localAudioUrl,
                                ArrayList<String> imgUrls) {
        this.title = title;
        this.summary = summary;
        this.targetUrl = targetUrl;
        this.imageUrl = imageUrl;
        this.localImgUrl = localImgUrl;
        this.audioUrl = audioUrl;
        this.localAudioUrl = localAudioUrl;
        this.imgUrls = imgUrls;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLocalImgUrl() {
        return localImgUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public String getLocalAudioUrl() {
        return localAudioUrl;
    }

    public ArrayList<String> getImgUrls() {
        return imgUrls;
    }



    public static class Builder {

        private String title = "分享标题";
        private String summary = "概述";
        private String targetUrl = "http://www.jandzy.com";
        private String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487611614204&di=5a257bb0e70762132ef1c0e25804829e&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253D580%2Fsign%3Dd750e91db9014a90813e46b599763971%2F3ca7811c8701a18b4e9144e09e2f07082938fe9f.jpg";
        private String localImgUrl = "/storage/emulated/0/tencent/QQfile_recv/11.png";
        private String audioUrl = "";
        private String localAudioUrl = "";
        private ArrayList<String> imgUrls = new ArrayList<>();

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder setTargetUrl(String targetUrl) {
            this.targetUrl = targetUrl;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setLocalImgUrl(String localImgUrl){
            this.localImgUrl = localImgUrl;
            return this;
        }

        public Builder setAudioUrl(String audioUrl){
            this.audioUrl = audioUrl;
            return this;
        }

        public Builder setLocalAudioUrl(String localAudioUrl){
            this.localAudioUrl = localAudioUrl;
            return this;
        }

        public Builder setImgUrls(ArrayList<String> imgUrls){
            this.imgUrls = imgUrls;
            return this;
        }




        public ShareContentMedia create() {
            ShareContentMedia shareContentMedia = new ShareContentMedia(title, summary, targetUrl,
                    imageUrl,localImgUrl,audioUrl,localAudioUrl,imgUrls);
            return shareContentMedia;
        }
    }
}
