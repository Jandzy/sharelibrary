package com.jandzy.sharelibrary.qq;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;

import com.jandzy.sharelibrary.PlatformConfig;
import com.jandzy.sharelibrary.IShareHandler;
import com.jandzy.sharelibrary.listener.AuthListener;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

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
        mTencent = Tencent.createInstance("", this.mContext);

    }

    @Override
    public void authorize(Activity activity, final AuthListener authListener) {
        qqBaseIUiListener = new QQBaseIUiListener(authListener,mTencent,mContext);
        mTencent.login(activity, "all", qqBaseIUiListener);
    }

    @Override
    public void authorize(Fragment fragment, AuthListener authListener) {
        qqBaseIUiListener = new QQBaseIUiListener(authListener,mTencent,mContext);
        mTencent.login(fragment,"all",qqBaseIUiListener);
    }

    @Override
    public void share() {

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,qqBaseIUiListener);
        }
    }

}
