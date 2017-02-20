package com.jandzy.sharelibrary.model.platformmodel;

import com.jandzy.sharelibrary.PlatformConfig;

/**
 * Created by jrazy on 2017/2/20.
 */
public class WXModel extends PlatformModel {
    public String app_id = null;

    @Override
    public String getAppId() {
        return app_id;
    }
}
