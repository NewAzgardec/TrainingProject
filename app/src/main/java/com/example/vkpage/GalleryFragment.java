package com.example.vkpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vkpage.asynctask.ImageTask;

public class GalleryFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        String my_url = "https://pp.userapi.com/c845120/v845120016/1ee785/WpxQh5WZT_Q.jpg";
        new ImageTask((ImageView)getView().findViewById(R.id.for_test)).execute(my_url);

    }
    }
