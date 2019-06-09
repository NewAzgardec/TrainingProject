package com.example.vkpage;

import android.widget.ImageView;

public class ImageLoader {
    private ImageView imageView;
    private String imageURL;

    public ImageLoader(ImageView imageView, String imageURL){
        this.imageView=imageView;
        this.imageURL=imageURL;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
