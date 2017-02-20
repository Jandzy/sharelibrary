package com.jandzy.sharelibrary.listener;

import com.jandzy.sharelibrary.PlatformConfig;

import java.util.Map;

/**
 * Created by jrazy on 2017/2/16.
 */
public interface AuthListener {
    void onComplete(PlatformConfig.PlatformType paramPlatformType, Map<String, String> paramMap);

    void onError(PlatformConfig.PlatformType paramPlatformType, String paramString);

    void onCancel(PlatformConfig.PlatformType paramPlatformType);
}
