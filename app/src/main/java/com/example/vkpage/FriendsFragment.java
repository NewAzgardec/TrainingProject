package com.example.vkpage;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiWall;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;
import com.vk.sdk.api.model.VKUsersArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.List;

public class FriendsFragment extends Fragment  {

    private ListView listOfFriends;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        listOfFriends = getView().findViewById(R.id.list_of_friends);
        //final ImageView photo =getView().findViewById(R.id.photo_for_friends);
        final VKRequest vkRequest = VKApi.friends().get(VKParameters.from("fields", "first_name, last_name", "order", "hints"));

        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKList vkList = (VKList) response.parsedModel;

                VKApiUser user = ((VKList<VKApiUser>)response.parsedModel).get(0);
              //  for(int i=0; i<vkList.size();i++) {
                    String url = user.photo_100;


                   // new ImageTask(photo).execute("https://pp.userapi.com/c845120/v845120016/1ee785/WpxQh5WZT_Q.jpg");
                    Log.i("lol", url);


               // }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.text_friends, R.id.text_for_friends, vkList);
                listOfFriends.setAdapter(adapter);
            }

        });
    }
//   private class ImageTask extends AsyncTask<String, Void, Bitmap>{
//        ImageView bitmapImageView;
//        public ImageTask(ImageView bitmapImageView){
//            this.bitmapImageView=bitmapImageView;
//        }
//
//       @Override
//       protected Bitmap doInBackground(String... strings) {
//           String urlDisplay = strings[0];
//           Bitmap icon=null;
//           try{
//               InputStream in = new java.net.URL(urlDisplay).openStream();
//               icon= BitmapFactory.decodeStream(in);
//           } catch (Exception e) {
//               e.printStackTrace();
//           }
//           return icon;
//       }
//       protected void onPostExecute(Bitmap result){
//            bitmapImageView.setImageBitmap(result);
//       }
//   }
}