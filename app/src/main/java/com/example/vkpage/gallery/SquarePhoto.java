package com.example.vkpage.gallery;

import android.content.Context;
import android.util.AttributeSet;

public class SquarePhoto extends android.support.v7.widget.AppCompatImageView {
    public SquarePhoto(Context context) {
        super(context);
    }

    public SquarePhoto(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquarePhoto(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }
}
