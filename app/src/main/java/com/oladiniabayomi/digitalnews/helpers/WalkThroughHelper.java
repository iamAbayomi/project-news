package com.oladiniabayomi.digitalnews.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class WalkThroughHelper {
    private final String INTRO ="intro";
    private SharedPreferences app_prefs;
    private Context context;

    public WalkThroughHelper(Context context){
        app_prefs = context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.context = context;
    }

    public void putIntro(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(INTRO, loginorout);
        edit.apply();
    }
    public String getIntro() {
        return app_prefs.getString(INTRO, "");
    }
}
