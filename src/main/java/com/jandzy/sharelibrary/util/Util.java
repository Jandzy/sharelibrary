package com.jandzy.sharelibrary.util;

import android.content.pm.PackageManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @descript 工具类
 */
public class Util {
    public static Map<String, String> jsonToMap(JSONObject val)
    {
        HashMap map = new HashMap();

        Iterator iterator = val.keys();
        while (iterator.hasNext())
        {
            String var4 = (String)iterator.next();
            map.put(var4, val.opt(var4) + "");
        }
        return map;
    }
}
