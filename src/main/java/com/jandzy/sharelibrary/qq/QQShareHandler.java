package com.jandzy.sharelibrary.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jandzy.sharelibrary.IShareHandler;
import com.jandzy.sharelibrary.PlatformConfig;
import com.jandzy.sharelibrary.listener.AuthListener;
import com.jandzy.sharelibrary.listener.QqAuthorizeIUiListener;
import com.jandzy.sharelibrary.share.IShareMedia;
import com.jandzy.sharelibrary.share.ShareWebMedia;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * QQ 登陆、分享实现类
 */
public class QQShareHandler implements IShareHandler {

    private Tencent mTencent;

    private Context mContext;

    private QqAuthorizeIUiListener qqAuthorizeIUiListener;

    private int shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;//qq分享类型

    @Override
    public void init(Context context) {
        this.mContext = context;
        mTencent = Tencent.createInstance(PlatformConfig.getAppId(PlatformConfig.PlatformType.QQ), this.mContext);
    }

    @Override
    public void authorize(Activity activity, AuthListener authListener) {
        qqAuthorizeIUiListener = new QqAuthorizeIUiListener(authListener, mTencent, mContext);
        mTencent.login(activity, "all", qqAuthorizeIUiListener);
    }

    @Override
    public void authorize(Fragment fragment, AuthListener authListener) {
        qqAuthorizeIUiListener = new QqAuthorizeIUiListener(authListener, mTencent, mContext);
        mTencent.login(fragment, "all", qqAuthorizeIUiListener);
    }


    @Override
    public void share(Activity activity,IShareMedia shareMedia) {
        Bundle params = new Bundle();
        if (shareMedia instanceof ShareWebMedia) {
            ShareWebMedia shareWebMedia = ((ShareWebMedia) shareMedia);
            params.putString(QQShare.SHARE_TO_QQ_TITLE,shareWebMedia.getTitle());
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY,shareWebMedia.getSummary());
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,shareWebMedia.getTargetUrl());
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,shareWebMedia.getImageUrl());
        }

        doShareToQQ(activity,params);
    }

    private void doShareToQQ(final Activity activity, final Bundle params) {
        // QQ分享要在主线程做
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                if (null != mTencent) {
                    mTencent.shareToQQ(activity, params, new IUiListener() {
                        @Override
                        public void onComplete(Object o) {

                        }

                        @Override
                        public void onError(UiError uiError) {

                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                }
            }
        });
    }

    /**
     *  调用QQ登陆的actiivty需要调用  ShareManger.onActivityResult()方法，否则，接收不到qq返回的信息
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, qqAuthorizeIUiListener);
        }
    }

    @Override
    public void onDestroy() {
        if (mTencent != null) {
            mTencent.releaseResource();
        }
    }
}
