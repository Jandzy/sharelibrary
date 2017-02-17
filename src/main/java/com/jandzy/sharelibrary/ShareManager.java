package com.jandzy.sharelibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.jandzy.sharelibrary.listener.AuthListener;

/**
 * Created by jrazy on 2017/2/16.
 */
public class ShareManager {

    private static ShareManager mShareManager = null;
    private static Context mContext = null;
    private IShareHandler mShareHandle;

    private ShareManager(Context context) {
        this.mContext = context;
    }

    public static ShareManager getInstance(Context context) {
        if (mShareManager == null) {
            mShareManager = new ShareManager(context);
        }
        return mShareManager;
    }

    private IShareHandler getShareHandler(PlatformType platformType){

        switch (platformType) {
            case WEIXIN:
                
                break;
            case QQ:

                break;
            case WEIXIN_CIRCLE:

                break;
            case QZONE:

                break;
            case SINA_WB:

                break;
        }

        return null;
    }

    public void doOauthVerify(Activity activity,PlatformType platformType, AuthListener authListener){

        this.mShareHandle = getShareHandler(platformType);
        mShareHandle.init(mContext);
        mShareHandle.authorize(activity,authListener);
    }

    public void doOauthVerify(Fragment fragment, PlatformType platformType, AuthListener authListener){

        this.mShareHandle = getShareHandler(platformType);
        mShareHandle.init(mContext);
        mShareHandle.authorize(fragment,authListener);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mShareHandle.onActivityResult(requestCode,resultCode,data);
    }
}
