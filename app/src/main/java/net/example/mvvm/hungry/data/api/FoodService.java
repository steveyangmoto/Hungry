package net.example.mvvm.hungry.data.api;

import net.example.mvvm.hungry.data.model.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FoodService {
    @GET("/v2/restaurant/")
    Call<List<Restaurant>> getRestaurants(@Query("lat") double lattitude, @Query("lng") double longitude, @Query("offset") int offset, @Query("limit") int limit);
}
