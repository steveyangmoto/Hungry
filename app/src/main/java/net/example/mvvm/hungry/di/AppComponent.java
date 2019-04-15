package net.example.mvvm.hungry.di;


import android.app.Application;

import com.squareup.picasso.Picasso;

import net.example.mvvm.hungry.HungryApplication;
import net.example.mvvm.hungry.ui.binding.ImageBindingAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBindingModule.class,
        DaggerBindingModule.class,
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(HungryApplication hungryApplication);
    DaggerBindingComponent.Builder daggerBindingComponentBuilder();
}