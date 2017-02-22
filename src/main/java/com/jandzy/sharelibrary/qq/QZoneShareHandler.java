package com.jandzy.sharelibrary.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jandzy.sharelibrary.IShareHandler;
import com.jandzy.sharelibrary.PlatformConfig;
import com.jandzy.sharelibrary.listener.AuthListener;
import com.jandzy.sharelibrary.listener.QqShareListener;
import com.jandzy.sharelibrary.share.IShareMedia;
import com.jandzy.sharelibrary.share.ShareDefaultMedia;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QzoneShare;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.Tencent;

/**
 * 分享到qq空间
 */
public class QZoneShareHandler implements IShareHandler {

    private Tencent mTencent;

    private Context mContext;

    private QqShareListener qqShareListener;//qq分享返回监听

    //QZone分享， SHARE_TO_QQ_TYPE_DEFAULT 图文，SHARE_TO_QQ_TYPE_IMAGE 纯图
    private int shareType = QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT;

    @Override
    public void init(Context context) {
        this.mContext = context;
        mTencent = Tencent.createInstance(PlatformConfig.getAppId(PlatformConfig.PlatformType.QQ), this.mContext);
    }

    @Override
    public void authorize(Activity activity, AuthListener authListener) {

    }

    @Override
    public void authorize(Fragment fragment, AuthListener authListener) {

    }

    @Override
    public void share(Activity activity, IShareMedia shareMedia, AuthListener authListener) {

        qqShareListener = new QqShareListener(authListener);

        Bundle params = new Bundle();

        if(shareMedia instanceof ShareDefaultMedia){

            ShareDefaultMedia shareDefaultMedia = ((ShareDefaultMedia) shareMedia);
            shareType =  QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT;
            params.putString(QzoneShare.SHARE_TO_QQ_TITLE, shareDefaultMedia.getTitle());
            params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, shareDefaultMedia.getSummary());
            params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, shareDefaultMedia.getTargetUrl());
//            params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, shareDefaultMedia.getImageUrl());
            params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, shareType);
            doShareToQzone(activity,params);
        }

    }

    /**
     * 用异步方式启动分享
     * @param params
     */
    private void doShareToQzone(final Activity activity, final Bundle params) {
        // QZone分享要在主线程做
        ThreadManager.getMainHandler().post(new Runnable() {

            @Override
            public void run() {
                if (null != mTencent) {
                    mTencent.shareToQzone(activity, params, qqShareListener);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_QZONE_SHARE) {
            Tencent.onActivityResultData(requestCode,resultCode,data,qqShareListener);
        }
    }

    @Override
    public void onDestroy() {

    }
}
