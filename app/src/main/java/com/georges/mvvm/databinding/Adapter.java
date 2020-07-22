package com.georges.mvvm.databinding;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Adapter {

    private Adapter() {

    }


    @BindingAdapter({"imageUrl", "circular"})
    public static void loadImage(ImageView imageView, String imageUrl, boolean circular) {
        if (circular)
            Glide.with(imageView.getContext()).load(imageUrl).apply(RequestOptions.circleCropTransform()).into(imageView);
        else
            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

    @BindingAdapter({"setVisibility"})
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

}
