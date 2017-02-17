package com.jandzy.sharelibrary.listener;

import java.util.Map;

/**
 * Created by jrazy on 2017/2/16.
 */
public interface AuthListener {
    void onComplete(int paramPlatformType, Map<String, String> paramMap);

    void onError(int paramPlatformType, String paramString);

    void onCancel(int paramPlatformType);
}
