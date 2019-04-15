package net.example.mvvm.hungry.data.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import net.example.mvvm.hungry.Constants;
import net.example.mvvm.hungry.HungryApplication;
import net.example.mvvm.hungry.data.model.ApiError;
import net.example.mvvm.hungry.data.model.DataWrapper;
import net.example.mvvm.hungry.data.model.Restaurant;
import net.example.mvvm.hungry.data.model.RestaurantReqParam;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import lombok.Getter;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static net.example.mvvm.hungry.Constants.CONNECT_TIME_OUT_SECONDS;
import static net.example.mvvm.hungry.Constants.READ_TIME_OUT_SECONDS;
import static net.example.mvvm.hungry.Constants.WRITE_TIME_OUT_SECONDS;
public class HungryApi {
    private FoodService service;
    private String baseUrl;
    @Getter
    private Picasso picasso;

    @Inject
    public HungryApi(FoodService service,Picasso picasso){
        this.service = service;
        this.picasso = picasso;
    }

    public LiveData<DataWrapper<List<Restaurant>>> getRestaurants(@NonNull RestaurantReqParam params){
        final MutableLiveData<DataWrapper<List<Restaurant>>> data = new MutableLiveData<>();
        final DataWrapper<List<Restaurant>> dataWrapper = new DataWrapper<>();
        service.getRestaurants(params.getLat(),params.getLng(),params.getOffset(),params.getLimit()).enqueue(new ApiCallback<List<Restaurant>>() {

            @Override
            protected void handleResponseData(List<Restaurant> responseObject) {
                data.setValue(dataWrapper.setData(responseObject));
            }

            @Override
            protected void handleError(Response<List<Restaurant>> errorResponse) {
                data.setValue(dataWrapper.setData(null).setApiError(new ApiError().setCode(errorResponse.code()).setMessage(errorResponse.message())));
            }

            @Override
            protected void handleException(Exception t) {
                data.setValue(dataWrapper.setData(null).setApiError(new ApiError().setCode(Constants.GENERAL_EXCEPTION_ERROR_CODE).setMessage(t.getMessage())));
            }
        });
        return data;
    }
}
