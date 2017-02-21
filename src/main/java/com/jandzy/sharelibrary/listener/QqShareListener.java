package com.jandzy.sharelibrary.listener;

import android.util.Log;

import com.jandzy.sharelibrary.PlatformConfig;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * Created by jrazy on 2017/2/21.
 */
public class QqShareListener implements IUiListener {

    private static final String TAG = "QqShareListener";

    private AuthListener authListener;

    public QqShareListener(AuthListener authListener){
        this.authListener = authListener;
    }

    @Override
    public void onComplete(Object o) {
        Log.e(TAG, "onComplete: " );
        authListener.onComplete(PlatformConfig.PlatformType.QQ, com.jandzy.sharelibrary.util.Util.jsonToMap((JSONObject) o));

    }

    @Override
    public void onError(UiError uiError) {
        Log.e(TAG, "onError: " );
        authListener.onError(PlatformConfig.PlatformType.QQ, uiError.toString());
    }

    @Override
    public void onCancel() {
        Log.e(TAG, "onCancel: " );
        authListener.onCancel(PlatformConfig.PlatformType.QQ);
    }
}
