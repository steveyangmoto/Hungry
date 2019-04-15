package net.example.mvvm.hungry.ui.restaurant;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import net.example.mvvm.hungry.Constants;
import net.example.mvvm.hungry.HungryApplication;
import net.example.mvvm.hungry.data.api.HungryApi;
import net.example.mvvm.hungry.data.model.DataWrapper;
import net.example.mvvm.hungry.data.model.Restaurant;
import net.example.mvvm.hungry.data.model.RestaurantReqParam;
import net.example.mvvm.hungry.ui.restaurant.list.RestaurantsAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class RestaurantViewModel extends AndroidViewModel implements RestaurantsAdapter.RestaurantClickListener {
    private RestaurantReqParam restaurantReqParam;
    private int currentoffset = 0;
    private List<Restaurant> restaurantList = new ArrayList<>();
    private MutableLiveData<RestaurantViewState> restaurantViewStateObservable = new MutableLiveData<>();
    private RestaurantsAdapter adapter;
    private boolean loading;
    private HungryApi api;

    public enum RestaurantViewState{
        PENDING_LOAD_RESTAURANTS,
        PENDING_LISTEN_TO_SCROLL_POSITION,
        PENDING_SHOW_PROGRESS_BAR,
        PENDING_HIDE_PROGRESS_BAR
    }

    @Inject
    public RestaurantViewModel(@NonNull HungryApi api,@NonNull Application application){
            super(application);
            this.api = api;
            restaurantViewStateObservable.setValue(RestaurantViewState.PENDING_LOAD_RESTAURANTS);
            loading = true;
            adapter = new RestaurantsAdapter(this);
    }

    public LiveData<RestaurantViewState> getRestaurantViewStateObservable(){
        return this.restaurantViewStateObservable;
    }

    public LiveData<DataWrapper<List<Restaurant>>> getRestaurantsObservable(){
        restaurantViewStateObservable.setValue(RestaurantViewState.PENDING_SHOW_PROGRESS_BAR);
        restaurantReqParam = new RestaurantReqParam().
                setLat(getLattitude()).
                setLng(getLongitude()).
                setOffset(currentoffset).
                setLimit(Constants.RESTAURANT_LIMIT_PER_LOAD);
        final LiveData<DataWrapper<List<Restaurant>>> restaurantsObservable = api.
                                                                            getRestaurants(restaurantReqParam);
        return restaurantsObservable;
    }

    public RestaurantsAdapter getAdapter(){
        return adapter;
    }

    public void handleRestaurantListResponse(DataWrapper<List<Restaurant>> response){
        if(response!=null && response.getApiError()==null){
            if(currentoffset == restaurantList.size()) {
                if(restaurantList.size()==0){
                    restaurantViewStateObservable.setValue(RestaurantViewState.PENDING_LISTEN_TO_SCROLL_POSITION);
                }
                restaurantList.addAll(response.getData());
                adapter.setRestaurantList(restaurantList);
            }
        }
        loading = false;
        restaurantViewStateObservable.setValue(RestaurantViewState.PENDING_HIDE_PROGRESS_BAR);
    }

    public void handleScrollChange(int offset,int range,int extent){
        int percentage = (int)(100.0 * offset / (float)(range - extent));
        if(percentage > 90){
            if(!loading) {
                currentoffset = restaurantList.size();
                restaurantViewStateObservable.setValue(RestaurantViewState.PENDING_LOAD_RESTAURANTS);
                loading = true;
            }
        }
    }

    private double getLattitude(){
        return Constants.LAT;
    }

    private double getLongitude(){
        return Constants.LNG;
    }

    @Override
    public void onRestaurantClick(Restaurant restaurant) {

    }
}
