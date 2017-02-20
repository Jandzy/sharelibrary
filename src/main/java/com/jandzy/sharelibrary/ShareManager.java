package com.jandzy.sharelibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.jandzy.sharelibrary.listener.AuthListener;
import com.jandzy.sharelibrary.qq.QQShareHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jrazy on 2017/2/16.
 */
public class ShareManager {

    private static ShareManager mShareManager = null;
    private static Context mContext = null;
    private IShareHandler mShareHandle;
    private Map<PlatformConfig.PlatformType, IShareHandler> mMapShareHandler = new HashMap<>();


    private ShareManager(Context context) {
        this.mContext = context;
    }

    public static ShareManager getInstance(Context context) {
        if (mShareManager == null) {
            mShareManager = new ShareManager(context);
        }
        return mShareManager;
    }

    private IShareHandler getShareHandler(PlatformConfig.PlatformType platformType) {

        if (mMapShareHandler.get(platformType) == null) {
            switch (platformType) {
                case WX:
                    break;
                case QQ:
                    mMapShareHandler.put(platformType,new QQShareHandler());
                    break;
                case WX_CIRCLE:

                    break;
                case QQZONE:

                    break;
                case SINA:

                    break;
            }
        }
        return mMapShareHandler.get(platformType);
    }

    public void doOauthVerify(Activity activity, PlatformConfig.PlatformType platformType, AuthListener authListener) {

        this.mShareHandle = getShareHandler(platformType);
        mShareHandle.init(mContext);
        mShareHandle.authorize(activity, authListener);
    }

    public void doOauthVerify(Fragment fragment, PlatformConfig.PlatformType platformType, AuthListener authListener) {

        this.mShareHandle = getShareHandler(platformType);
        mShareHandle.init(mContext);
        mShareHandle.authorize(fragment, authListener);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mShareHandle.onActivityResult(requestCode, resultCode, data);
    }
}
