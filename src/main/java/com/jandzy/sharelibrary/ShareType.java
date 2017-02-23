package com.jandzy.sharelibrary;

/**
 * 分享类型
 * <p>因为不同的平台或者类型分享的属性不一定，所以，根据不同的类型，从ShareContenMedia类中取对应的属性。</p>
 *
 * <p>其中imgUrl既可以是网络url也可以是本地url，localImgUrl只能是本地图片的url</p>
 *
 * <p>例如，QQ_TYPE_DEFAULT 代表ShareContentMedia对象中只有title、summary、targetUrl、imgUrl、appName这几个
 * 属性的值被添加到分享内容中，其他属性即使赋值也不会分享出来。</p>
 *
 */

public class ShareType {

    //分享到QQ  title、summary、targetUrl、imgUrl、appName
    public static  final int QQ_TYPE_DEFAULT = 0;

    //分享到QQ  title、summary、targetUrl、imgUrl、appName、audioUrl
    public static  final int QQ_TYPE_AUDIO = 1;

    //分享到QQ  localImgUrl
    public static  final int QQ_TYPE_LOCAL_IMAGE_URL = 2;

    //分享到QQ（应用）  title、summary、imgUrl、appName
    public static  final int QQ_TYPE_APPLICATION = 3;

    //分享到Qzone(转发)  title、summary、targetUrl、imageUrls
    public static  final int QZONE_TYPE_IMAGE_TEXT = 4;

    //分享到Qzone(说说) summary、imageUrls
    public static  final int QZONE_TYPE_SHUOSHUO = 5;

    //分享到Qzone(视频) summary、localAudioUrl
    public static  final int QZONE_TYPE_AUDIO = 6;
}
