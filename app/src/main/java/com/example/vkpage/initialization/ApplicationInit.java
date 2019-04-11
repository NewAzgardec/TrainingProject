package com.example.vkpage;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

public class ApplicationInit extends Application {

    private SharedPreferences preferences;
    private static String token = "TOKEN";
    private String retrivedToken;
    private Context context = this;
    VKAccessTokenTracker vkAccessToken = new VKAccessTokenTracker() {
         @Override
         public void onVKAccessTokenChanged(@Nullable VKAccessToken oldToken, @Nullable VKAccessToken newToken) {
             if (newToken == null) {

                 preferences = context.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                 preferences.edit().putString("VK_TOKEN", token).apply();

                 Intent intent = new Intent(ApplicationInit.this, MainActivity.class);
                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(intent);

             }
         }
    };

    @Override
    public void onCreate(){
        super.onCreate();

        preferences=context.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        retrivedToken = preferences.getString("VK_TOKEN",null);
//        if (!retrivedToken.equals(vkAccessToken.toString())) {
//            token.replace((CharSequence) vkAccessToken, retrivedToken);
//            retrivedToken.toString() == vkAccessToken;
//        }
        vkAccessToken.startTracking();
        VKSdk.initialize(this);
    }
}
