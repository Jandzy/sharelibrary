package com.jandzy.sharelibrary;

import java.util.HashMap;
import java.util.Map;

import static com.jandzy.sharelibrary.PlatformConfig.PlatformType.*;


/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class PlatformConfig {
    private static Map<PlatformType,String> mConfig = new HashMap<>();

    static {
        mConfig.put(QQ,"");
        mConfig.put(WX,"");
        mConfig.put(WX_CIRCLE,"");
        mConfig.put(QQZONE,"");
        mConfig.put(SINA,"");
    }

    public static void setAppId(PlatformType platformType,String appid){
        switch (platformType){
            case QQ:
                mConfig.put(QQ,appid);
                break;
            case WX:
                mConfig.put(WX,appid);
                break;
            case WX_CIRCLE:
                mConfig.put(WX_CIRCLE,appid);
                break;
            case QQZONE:
                mConfig.put(QQZONE,appid);
                break;
            case SINA:
                mConfig.put(SINA,appid);
                break;
        }
    }

    /**
     * Created by jrazy on 2017/2/16.
     */
    public static enum  PlatformType {
        WX,  WX_CIRCLE,  QQ,  QQZONE,  SINA;

         PlatformType() {

        }

    }
}
