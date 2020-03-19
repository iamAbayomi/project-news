package com.oladiniabayomi.digitalnews.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class SignInHelper {
    private final String LOGIN= "login";
    private SharedPreferences signIn_prefs;
    private Context context;

    private final String fragments= "frag";


    public SignInHelper(Context context){
        signIn_prefs =context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.context =context;
    }

    public void putLogin(String login){
        SharedPreferences.Editor edit = signIn_prefs.edit();
        edit.putString(LOGIN, login);
        edit.apply();
    }

    public String getLogin(){
        return  signIn_prefs.getString(LOGIN, "");
    }

    public void addFragments(String Fragments){
        SharedPreferences.Editor edit = signIn_prefs.edit();
        edit.putString(fragments, Fragments ) ;
        edit.apply();
    }

    public String getFragments(){
        return signIn_prefs.getString(fragments,"");
    }
}
