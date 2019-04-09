package com.example.vkpage;

import android.app.Application;
import android.content.Intent;

import com.vk.api.sdk.VK;
import com.vk.api.sdk.auth.VKAccessToken;

public class ApplicationInit extends Application {


     VKAccessToken vkAccessToken = new VKAccessToken(){


        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {

                Intent intent = new Intent(ApplicationInit.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onCreate(){
        super.onCreate();

         vkAccessToken.getAccessToken();
        VK.initialize(this);



    }
}
