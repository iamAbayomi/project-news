package com.oladiniabayomi.digitalnews;

import android.app.Application;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;

public class ApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        String appToken = "aorsgbhwgm4g";
        String environment = AdjustConfig.ENVIRONMENT_SANDBOX;
        AdjustConfig config = new AdjustConfig(this, appToken, environment);
        Adjust.onCreate(config);
    }
}
