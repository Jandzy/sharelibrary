package com.jandzy.sharelibrary;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;

import com.jandzy.sharelibrary.listener.AuthListener;

/**
 *
 */
public interface IShareHandler {

    void init(Context context);

    void authorize(Activity activity, AuthListener authListener);
    void authorize(Fragment fragment, AuthListener authListener);

    void share();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onDestroy();
}
