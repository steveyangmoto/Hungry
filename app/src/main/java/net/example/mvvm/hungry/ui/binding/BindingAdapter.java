package net.example.mvvm.hungry.ui.binding;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import net.example.mvvm.hungry.HungryApplication;
import net.example.mvvm.hungry.R;

public class BindingAdapter {
    private BindingAdapter(){}
    @android.databinding.BindingAdapter("imageResource")
    public static void setImageResource(ImageView view, @NonNull int resource) {
        view.setImageResource(resource);
    }

    @android.databinding.BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, @NonNull String imageUrl) {
        imageView.setBackgroundColor(Color.TRANSPARENT);
        HungryApplication.getApplication().getApi().getPicasso()
                .load(imageUrl)
                .into(imageView);
    }
}
