package com.jandzy.sharelibrary.qq;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.jandzy.sharelibrary.PlatformConfig;
import com.jandzy.sharelibrary.listener.AuthListener;
import com.jandzy.sharelibrary.util.Util;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * Created by jrazy on 2017/2/16.
 */
public class QQBaseIUiListener implements IUiListener {

    private AuthListener authListener;
    private Tencent mTencent;
    private Context mContext;

    private UserInfo mUserInfo = null;


    public QQBaseIUiListener(AuthListener authListener, Tencent tencent,Context context){
        this.authListener = authListener;
        this.mTencent = tencent;
        this.mContext = context;
    }

    @Override
    public void onComplete(Object o) {
        if ((null == o) || ((JSONObject)o == null))
        {
            return;
        }
        JSONObject response = (JSONObject)o;
        initOpenidAndToken(response);
        
        mUserInfo = new UserInfo(mContext, mTencent.getQQToken());
        if(!ready(mContext)){
            return;
        }
        mUserInfo.getUserInfo(useInfoListener);
    }

    public boolean ready(Context context) {
        if (mTencent == null) {
            return false;
        }
        boolean ready = mTencent.isSessionValid()
                && mTencent.getQQToken().getOpenId() != null;
        if (!ready) {
            Toast.makeText(context, "login and get openId first, please!",
                    Toast.LENGTH_SHORT).show();
        }
        return ready;
    }

    @Override
    public void onError(UiError uiError) {
        useInfoListener.onError(uiError);
    }

    @Override
    public void onCancel() {
        useInfoListener.onCancel();
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
        } catch(Exception e) {
        }
    }

    private IUiListener useInfoListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            authListener.onComplete(PlatformConfig.PlatformType.QQ, Util.jsonToMap((JSONObject) o));
        }

        @Override
        public void onError(UiError uiError) {
            authListener.onError(PlatformConfig.PlatformType.QQ, uiError.toString());
        }

        @Override
        public void onCancel() {
            authListener.onCancel(PlatformConfig.PlatformType.QQ);
        }
    };
}
