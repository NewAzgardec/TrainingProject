package com.example.vkpage.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vkpage.R;
import com.example.vkpage.asynctask.CircleImageTask;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private String TAG = "TAG";
    private Button logout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        final VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name, photo_200, online"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                final VKList<VKApiUser> userMe = ((VKList<VKApiUser>) response.parsedModel);

                CircleImageView im = getView().findViewById(R.id.mainPhoto);
                new CircleImageTask(im).execute(userMe.get(0).photo_200);

                TextView txt = getView().findViewById(R.id.userName);
                String fullName = userMe.get(0).first_name + " " + userMe.get(0).last_name;
                txt.setText(fullName);


                TextView online = getView().findViewById(R.id.onlineStatus);

                if (userMe.get(0).online) {
                    online.setText(R.string.online_status);
                } else {
                    online.setText(R.string.offline_status);
                }
            }
        });

        logout = getView().findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Logout");
                VKSdk.logout();
                System.exit(0);
            }
        });

    }

}
