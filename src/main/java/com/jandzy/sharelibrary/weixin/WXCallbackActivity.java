package com.jandzy.sharelibrary.weixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.jandzy.sharelibrary.PlatformConfig;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 微信回调
 */
public class WXCallbackActivity extends Activity implements IWXAPIEventHandler {

    //IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //通过WXAPI工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, PlatformConfig.getAppId(PlatformConfig.PlatformType.WX),true);
        //将应用的appId注册到微信
        api.registerApp(PlatformConfig.getAppId(PlatformConfig.PlatformType.WX));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:  //成功
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED: //认证失败
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                break;
            default:
                break;
        }

    }
}
