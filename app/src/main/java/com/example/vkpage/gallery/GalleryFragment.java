package com.example.vkpage.gallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.vkpage.R;

import java.util.List;


public class GalleryFragment extends Fragment implements GalleryObserver {

    private GridView listOfPhotos;
    private List<GalleryModel> galleryList;
    private GalleryList galleryModel;
    private GalleryAdapter galleryAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        listOfPhotos = view.findViewById(R.id.list_of_photos);
        galleryAdapter = new GalleryAdapter(getContext(), galleryList);
        listOfPhotos.setAdapter(galleryAdapter);

        return view;
    }


    public GalleryFragment() {
        galleryModel = new GalleryList();
        galleryModel.setObserver(this);
        galleryModel.requestGallery();
        galleryList = galleryModel.getGalleryList();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    }

    @Override
    public void update() {
        galleryAdapter.notifyDataSetChanged();

    }
}
