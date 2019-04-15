package net.example.mvvm.hungry.di;

import net.example.mvvm.hungry.ui.restaurant.RestaurantActivity;
import net.example.mvvm.hungry.ui.restaurant.RestaurantViewModel;
import net.example.mvvm.hungry.ui.restaurant.list.RestaurantItemViewModel;

import dagger.Subcomponent;
@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    RestaurantViewModel restaurantViewModel();
}
