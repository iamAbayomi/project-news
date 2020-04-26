package com.oladiniabayomi.digitalnews;

import android.app.Application;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.onesignal.OneSignal;

public class ApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


        String appToken = "aorsgbhwgm4g";
        String environment = AdjustConfig.ENVIRONMENT_SANDBOX;
        AdjustConfig config = new AdjustConfig(this, appToken, environment);
        Adjust.onCreate(config);
    }
}
