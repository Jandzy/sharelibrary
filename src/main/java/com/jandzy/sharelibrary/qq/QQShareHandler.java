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
import com.jandzy.sharelibrary.listener.QqShareListener;
import com.jandzy.sharelibrary.share.IShareMedia;
import com.jandzy.sharelibrary.share.ShareImgMedia;
import com.jandzy.sharelibrary.share.ShareAudioMedia;
import com.jandzy.sharelibrary.share.ShareDefaultMedia;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.Tencent;

/**
 * QQ 登陆、分享实现类
 */
public class QQShareHandler implements IShareHandler {
    private static final String TAG = "QQShareHandler";

    private Tencent mTencent;

    private Context mContext;

    private QqAuthorizeIUiListener qqAuthorizeIUiListener;  //qq登陆返回监听

    private QqShareListener qqShareListener;//qq分享返回监听

    private int shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;//qq分享类型

    /**
     *     if ((mExtarFlag & QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN) != 0) {
     *          showToast("在好友选择列表会自动打开分享到qzone的弹窗~~~");
     *     } else if ((mExtarFlag & QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE) != 0) {
     *          showToast("在好友选择列表隐藏了qzone分享选项~~~");
     *     }
     */
    private int mExtarFlag = 0x00;//qq分享时候是否显示qq空间


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
    public void share(Activity activity,IShareMedia shareMedia,AuthListener authListener) {
        Bundle params = new Bundle();

        //默认分享格式
        if (shareMedia instanceof ShareDefaultMedia) {
            ShareDefaultMedia shareDefaultMedia = ((ShareDefaultMedia) shareMedia);

            shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;

            params.putString(QQShare.SHARE_TO_QQ_TITLE, shareDefaultMedia.getTitle());
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareDefaultMedia.getSummary());
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, shareDefaultMedia.getTargetUrl());
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, shareDefaultMedia.getImageUrl());

        }

        //带audio的分享
        if (shareMedia instanceof ShareAudioMedia) {
            ShareAudioMedia musiceMedia = ((ShareAudioMedia) shareMedia);

            shareType = QQShare.SHARE_TO_QQ_TYPE_AUDIO;

            params.putString(QQShare.SHARE_TO_QQ_TITLE,musiceMedia.getTitle());
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY,musiceMedia.getSummary());
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,musiceMedia.getTargetUrl());
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,musiceMedia.getImageUrl());
            params.putString(QQShare.SHARE_TO_QQ_AUDIO_URL,musiceMedia.getAudioUrl());
        }

        /**
         *  纯照片分享只支持本地照片分享
         */
        if (shareMedia instanceof ShareImgMedia) {
            ShareImgMedia imgMedia = ((ShareImgMedia) shareMedia);

            shareType = QQShare.SHARE_TO_QQ_TYPE_IMAGE;

            params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,imgMedia.getLocalImgUrl());
        }

        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, mExtarFlag);
        qqShareListener = new QqShareListener(authListener);
        doShareToQQ(activity,params);
    }


    private void doShareToQQ(final Activity activity, final Bundle params) {
        // QQ分享要在主线程做
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                if (null != mTencent) {
                    mTencent.shareToQQ(activity, params, qqShareListener);
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

        if (requestCode == Constants.REQUEST_QQ_SHARE) {
            Tencent.onActivityResultData(requestCode,resultCode,data,qqShareListener);
        }
    }

    @Override
    public void onDestroy() {
        if (mTencent != null) {
            mTencent.releaseResource();
        }
    }
}
