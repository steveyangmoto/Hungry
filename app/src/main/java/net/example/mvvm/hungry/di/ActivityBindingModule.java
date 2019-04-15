package net.example.mvvm.hungry.di;

import net.example.mvvm.hungry.ui.restaurant.RestaurantActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    public abstract RestaurantActivity restaurantActivity();
}
