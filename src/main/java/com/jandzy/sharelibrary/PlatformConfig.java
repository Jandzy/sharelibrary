package com.jandzy.sharelibrary;

import com.jandzy.sharelibrary.model.platformmodel.PlatformModel;
import com.jandzy.sharelibrary.model.platformmodel.QQModel;
import com.jandzy.sharelibrary.model.platformmodel.SinaModel;
import com.jandzy.sharelibrary.model.platformmodel.WXModel;

import java.util.HashMap;
import java.util.Map;

import static com.jandzy.sharelibrary.PlatformConfig.PlatformType.*;


/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class PlatformConfig {
    private static Map<PlatformType,PlatformModel> mConfig = new HashMap<>();

    static {
        mConfig.put(QQ,new QQModel());
        mConfig.put(WX,new WXModel());
        mConfig.put(WX_CIRCLE,new WXModel());
        mConfig.put(QQZONE,new QQModel());
        mConfig.put(SINA,new SinaModel());
    }

    public static void addWxAppId(String appid){
        WXModel wxModel = (WXModel) mConfig.get(WX);
        wxModel.app_id = appid;
    }

    public static void addQQId(String appid){
        QQModel qqModel = (QQModel) mConfig.get(QQ);
        qqModel.app_id = appid;
    }

    public static String getAppId(PlatformType platformType){
        return mConfig.get(platformType).getAppId();
    }

    /**
     * 平台类型
     */
    public static enum  PlatformType {
        WX,  WX_CIRCLE,  QQ,  QQZONE,  SINA;

         PlatformType() {

        }

    }
}
