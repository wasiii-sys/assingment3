package com.example.android3;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences pref;
    Context context;
    String APP_NAME="app_pref";
    final String IS_LOGIN="testname";
    final String EMAIL="email";

    public SessionManager(Context context){
        this.context=context;
        pref=context.getSharedPreferences(APP_NAME,context.MODE_PRIVATE);
    }

    public void setlogin(Boolean isloggedIN){
        pref.edit().putBoolean(IS_LOGIN,isloggedIN).apply();
    }

    public boolean getLogin(){
        return pref.getBoolean(IS_LOGIN,false);
    }
    public void setEmail(String setEmail){
        pref.edit().putString("email",setEmail).apply();
    }

    public String getEmail() {
        return pref.getString("email" , "");
    }

}