package com.jandzy.sharelibrary.share;

/**
 * Created by jrazy on 2017/2/20.
 */
public class ShareMusicMedia implements IShareMedia {

    private String title = "分享标题";
    private String summary  = "分享子标题";
    private String targetUrl = "http://www.jandzy.com";
    private String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487611614204&di=5a257bb0e70762132ef1c0e25804829e&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253D580%2Fsign%3Dd750e91db9014a90813e46b599763971%2F3ca7811c8701a18b4e9144e09e2f07082938fe9f.jpg";
    private String audioUrl = "";

    protected ShareMusicMedia(String title, String summary, String targetUrl, String imageUrl,String audioUrl) {
        this.title = title;
        this.summary = summary;
        this.targetUrl = targetUrl;
        this.imageUrl = imageUrl;
        this.audioUrl = audioUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getTitle() {
        return title;
    }

    public static class Builder{

        private String title = "分享标题";
        private String summary  = "分享子标题";
        private String targetUrl = "http://www.jandzy.com";
        private String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487611614204&di=5a257bb0e70762132ef1c0e25804829e&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253D580%2Fsign%3Dd750e91db9014a90813e46b599763971%2F3ca7811c8701a18b4e9144e09e2f07082938fe9f.jpg";
        private String audioUrl = "";

        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        public Builder setSummary(String summary){
            this.summary = summary;
            return this;
        }

        public Builder setTargetUrl(String targetUrl){
            this.targetUrl = targetUrl;
            return this;
        }

        public Builder setImageUrl(String imageUrl){
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setAudioUrl(String audioUrl){
            this.audioUrl = audioUrl;
            return this;
        }

        public ShareMusicMedia create(){
            ShareMusicMedia shareMusicMedia = new ShareMusicMedia(title,summary,targetUrl,imageUrl,audioUrl);
            return shareMusicMedia;
        }
    }
}
