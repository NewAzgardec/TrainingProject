package com.example.vkpage.friends;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.ArrayList;
import java.util.List;

class FriendsList {

    private List<FriendsModel> friendList;
    private List<FriendsObserver> observerList;

    FriendsList() {
        friendList = new ArrayList<>();
        observerList = new ArrayList<>();
    }

    void setObserver(FriendsObserver observer) {
        observerList.add(observer);
    }

    List<FriendsModel> getFriendList() {
        return friendList;
    }

    void requestFriends() {
        VKRequest request = VKApi.friends().get(VKParameters.from("fields", "first_name, photo_200", "order", "hints"));
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
                    FriendsModel friend = gson.fromJson(data, FriendsModel.class);
                    friendList.add(friend);
                }

                for (int i = 0; i < observerList.size(); i++) {
                    observerList.get(i).update();
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