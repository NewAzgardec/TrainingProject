package com.example.vkpage.initialization;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.vkpage.MainActivity;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

public class ApplicationInit extends Application {

    VKAccessTokenTracker vkAccessToken = new VKAccessTokenTracker() {
         @Override
         public void onVKAccessTokenChanged(@Nullable VKAccessToken oldToken, @Nullable VKAccessToken newToken) {
             if (newToken == null) {

                 Toast.makeText(ApplicationInit.this,"Token is invalid", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(ApplicationInit.this, MainActivity.class);
                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(intent);

             }}
    };

    @Override
    public void onCreate(){
        super.onCreate();
        vkAccessToken.startTracking();
        VKSdk.initialize(this);
    }
}
