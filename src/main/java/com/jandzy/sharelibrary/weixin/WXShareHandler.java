package com.jandzy.sharelibrary.weixin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.jandzy.sharelibrary.IShareHandler;
import com.jandzy.sharelibrary.PlatformConfig;
import com.jandzy.sharelibrary.listener.AuthListener;
import com.jandzy.sharelibrary.sharecontent.ShareContentMedia;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 微信分享
 */
public class WXShareHandler implements IShareHandler {

    //IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    public void init(Context context) {
        //通过WXAPI工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(context, PlatformConfig.getAppId(PlatformConfig.PlatformType.WX),true);
        //将应用的appId注册到微信
        api.registerApp(PlatformConfig.getAppId(PlatformConfig.PlatformType.WX));
    }

    @Override
    public void authorize(Activity activity, AuthListener authListener) {
        // send oauth request
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "none";
        api.sendReq(req);
        activity.finish();
    }

    @Override
    public void authorize(Fragment fragment, AuthListener authListener) {

    }

    @Override
    public void share(Activity activity, ShareContentMedia contentMedia, int type, AuthListener authListener) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onDestroy() {

    }
}
