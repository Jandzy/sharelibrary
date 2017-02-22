package com.jandzy.sharelibrary;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;

import com.jandzy.sharelibrary.listener.AuthListener;
import com.jandzy.sharelibrary.sharecontent.ShareContentMedia;

/**
 *  接口
 */
public interface IShareHandler {

    void init(Context context);

    void authorize(Activity activity, AuthListener authListener);
    void authorize(Fragment fragment, AuthListener authListener);

    /**
     *
     * @param activity
     * @param contentMedia 分享内容
     * @param type  对应ShareType中的属性
     * @param authListener  返回的回调借口
     */
    void share(Activity activity, ShareContentMedia contentMedia,int type, AuthListener authListener);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onDestroy();
}
