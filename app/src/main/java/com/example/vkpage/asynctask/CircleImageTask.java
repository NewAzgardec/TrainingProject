package com.example.vkpage.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class CircleImageTask extends AsyncTask<String, Void, Bitmap> {
    private CircleImageView bitmapImageView;

    public CircleImageTask(CircleImageView bitmapImageView) {
        this.bitmapImageView = bitmapImageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String urlDisplay = strings[0];
        Bitmap icon = null;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            icon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return icon;
    }

    protected void onPostExecute(Bitmap result) {
        bitmapImageView.setImageBitmap(result);
    }
}
