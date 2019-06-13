package com.example.vkpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vkpage.asynctask.ImageTask;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKPhotoArray;

import de.hdodenhof.circleimageview.CircleImageView;

public class GalleryFragment extends Fragment {

    private VKPhotoArray array;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        VKRequest request = new VKRequest("photos.getAll", VKParameters.from(VKApiConst.OWNER_ID), VKPhotoArray.class);
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                array = (VKPhotoArray) response.parsedModel;

                for (VKApiPhoto ava : array) {
                    CircleImageView galleryPhoto = getView().findViewById(R.id.for_test);
                    new ImageTask(galleryPhoto).execute(ava.photo_604);
                }
            }
        });

    }
}
