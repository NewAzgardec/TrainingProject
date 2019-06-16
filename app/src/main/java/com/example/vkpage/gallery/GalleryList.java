package com.example.vkpage.gallery;

import com.example.vkpage.IObserver;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;

import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKPhotoArray;

import java.util.ArrayList;
import java.util.List;

class GalleryList {

    private List<GalleryModel> galleryList;
    private List<IObserver> observerListGallery;

    GalleryList() {
        galleryList = new ArrayList<>();
        observerListGallery = new ArrayList<>();
    }

    void setObserver(IObserver observer) {
        observerListGallery.add(observer);
    }

    List<GalleryModel> getGalleryList() {
        return galleryList;
    }

    void requestGallery() {

        VKRequest request = new VKRequest("photos.getAll", VKParameters.from(VKApiConst.COUNT, "200"), VKPhotoArray.class);
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                String responseStr = response.responseString;
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = parser.parse(responseStr).getAsJsonObject().getAsJsonObject("response");
                JsonArray items = jsonObject.getAsJsonArray("items");

                for (JsonElement data : items) {
                    GalleryModel photo = gson.fromJson(data, GalleryModel.class);
                    galleryList.add(photo);
                }

                for (int i = 0; i < observerListGallery.size(); i++) {
                    observerListGallery.get(i).update();
                }
            }


            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                super.attemptFailed(request, attemptNumber, totalAttempts);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });
    }

}
