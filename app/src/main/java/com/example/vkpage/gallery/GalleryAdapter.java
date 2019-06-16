package com.example.vkpage.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.vkpage.R;
import com.example.vkpage.asynctask.ImageTask;

import java.util.List;

public class GalleryAdapter extends BaseAdapter {

    private List<GalleryModel> galleryList;
    private LayoutInflater layoutInflater;

    GalleryAdapter(Context context, List<GalleryModel> galleryList) {
        this.galleryList = galleryList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return galleryList.size();
    }

    @Override
    public Object getItem(int position) {
        return galleryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.text_gallery, parent, false);
        }

        GalleryModel photo = (GalleryModel) getItem(position);

        SquarePhoto albumPhoto = view.findViewById(R.id.for_test_gallery);
        new ImageTask(albumPhoto).execute(photo.photo_604);


        return view;
    }
}