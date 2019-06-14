package com.example.vkpage.theme;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences prefs;

    public SharedPref(Context context){
        prefs= context.getSharedPreferences("file", Context.MODE_PRIVATE);
    }

    public void setNightMode(Boolean state){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("NightMode", state);
        editor.apply();
    }

    public Boolean loadNightMode(){
        Boolean state = prefs.getBoolean("NightMode",false);
        return  state;
    }
}
