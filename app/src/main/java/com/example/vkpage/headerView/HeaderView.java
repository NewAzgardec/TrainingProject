package com.example.vkpage.headerView;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vkpage.R;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

public class HeaderView extends LinearLayout {

    private ImageView userImage;

    public HeaderView(Context context) {
        this(context, null);
    }

    public HeaderView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HeaderView(Context context, @Nullable AttributeSet attributeSet, int defStyleAttribute) {
        super(context, attributeSet, defStyleAttribute);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        inflate(getContext(), R.layout.header_compound, this);
        final VKRequest request= VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VKList<VKApiUser> userMe = ((VKList<VKApiUser>) response.parsedModel);
                TextView txt = findViewById(R.id.name_navigation);
                String fullName = userMe.get(0).first_name + " " + userMe.get(0).last_name;
                txt.setText(fullName);

            }
        });
        userImage = findViewById(R.id.android_icon);
    }

    public void updateImage(String colorCode) {
        try {
            int color = Color.parseColor(colorCode);
            userImage.setColorFilter(color);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}