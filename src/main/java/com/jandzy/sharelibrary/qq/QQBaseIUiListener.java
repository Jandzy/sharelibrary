package com.jandzy.sharelibrary.qq;

import android.content.Context;
import android.text.TextUtils;

import com.jandzy.sharelibrary.listener.AuthListener;
import com.jandzy.sharelibrary.util.Util;
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

    public QQBaseIUiListener(AuthListener authListener, Tencent tencent,Context context){
        this.authListener = authListener;
        this.mTencent = tencent;
        this.mContext = context;
    }

    @Override
    public void onComplete(Object o) {
        if ((null == o) || ((JSONObject)o == null))
        {
            authListener.onError(1, "onComplete response=null");
            return;
        }
        JSONObject response = (JSONObject)o;
        authListener.onComplete(1, Util.jsonToMap((JSONObject) o));
        initOpenidAndToken(response);
        mTencent.logout(mContext);
    }

    @Override
    public void onError(UiError uiError) {
        authListener.onError(1,uiError.toString());
    }

    @Override
    public void onCancel() {
        authListener.onCancel(1);
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
}
