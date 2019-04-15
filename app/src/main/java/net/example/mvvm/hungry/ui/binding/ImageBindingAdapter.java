package net.example.mvvm.hungry.ui.binding;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.example.mvvm.hungry.di.AppInjector;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class ImageBindingAdapter {

    private final Picasso picasso;
    @Inject
    public ImageBindingAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @android.databinding.BindingAdapter({"bind:imageUrl"})
    public void loadImage(ImageView imageView, @NonNull String imageUrl) {
        imageView.setBackgroundColor(Color.TRANSPARENT);
        picasso.load(imageUrl)
                .into(imageView);
    }
}