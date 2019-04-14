package net.example.mvvm.hungry.ui.restaurant.list;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import net.example.mvvm.hungry.HungryApplication;
import net.example.mvvm.hungry.data.model.Restaurant;
import net.example.mvvm.hungry.ui.base.BaseViewModel;

public class RestaurantItemViewModel extends AndroidViewModel {
    private MutableLiveData<Restaurant> restaurant = new MutableLiveData<>();
    private RestaurantsAdapter.RestaurantClickListener listener;

    public RestaurantItemViewModel(RestaurantsAdapter.RestaurantClickListener listener, Restaurant restaurant){
        super(HungryApplication.getApplication());
        this.listener = listener;
        this.restaurant.setValue(restaurant);
    }

    public LiveData<Restaurant> getRestaurant(){
        return restaurant;
    }


    public void onItemClick(){
        if(listener!=null){
            listener.onRestaurantClick(restaurant.getValue());
        }
    }
}
