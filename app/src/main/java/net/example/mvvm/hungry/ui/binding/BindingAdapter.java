package net.example.mvvm.hungry.ui.binding;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.example.mvvm.hungry.HungryApplication;
import net.example.mvvm.hungry.R;
import net.example.mvvm.hungry.data.api.HungryApi;
import net.example.mvvm.hungry.di.AppComponent;
import net.example.mvvm.hungry.di.AppInjector;
import net.example.mvvm.hungry.di.AppModule;

import javax.inject.Inject;

public class BindingAdapter {
    private BindingAdapter() {
    }

    @android.databinding.BindingAdapter("imageResource")
    public static void setImageResource(ImageView view, @NonNull int resource) {
        view.setImageResource(resource);
    }

}
