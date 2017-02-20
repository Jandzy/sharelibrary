package com.jandzy.sharelibrary.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.jandzy.sharelibrary.IShareHandler;
import com.jandzy.sharelibrary.PlatformConfig;
import com.jandzy.sharelibrary.listener.AuthListener;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import org.json.JSONObject;

/**
 * Created by jrazy on 2017/2/16.
 */
public class QQShareHandler implements IShareHandler {

    private Tencent mTencent;
    private Context mContext;

    private QQBaseIUiListener qqBaseIUiListener;

    @Override
    public void init(Context context) {
        this.mContext = context;
        mTencent = Tencent.createInstance(PlatformConfig.getAppId(PlatformConfig.PlatformType.QQ), this.mContext);
    }

    @Override
    public void authorize(Activity activity, AuthListener authListener) {
        qqBaseIUiListener = new QQBaseIUiListener(authListener, mTencent, mContext);
        mTencent.login(activity, "all", qqBaseIUiListener);
    }

    @Override
    public void authorize(Fragment fragment, AuthListener authListener) {
        qqBaseIUiListener = new QQBaseIUiListener(authListener, mTencent, mContext);
        mTencent.login(fragment, "all", qqBaseIUiListener);
    }


    @Override
    public void share() {

    }

    private void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, qqBaseIUiListener);
        }
    }

}
