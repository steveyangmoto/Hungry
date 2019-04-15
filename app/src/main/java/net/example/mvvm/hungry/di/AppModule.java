package net.example.mvvm.hungry.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import net.example.mvvm.hungry.Constants;
import net.example.mvvm.hungry.HungryApplication;
import net.example.mvvm.hungry.data.api.FoodService;
import net.example.mvvm.hungry.data.api.HostSelectionInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static net.example.mvvm.hungry.Constants.CONNECT_TIME_OUT_SECONDS;
import static net.example.mvvm.hungry.Constants.READ_TIME_OUT_SECONDS;
import static net.example.mvvm.hungry.Constants.WRITE_TIME_OUT_SECONDS;

@Module(subcomponents = {ViewModelSubComponent.class})
public class AppModule {

    @Singleton
    @Provides
    HostSelectionInterceptor provideHostSelectionInterceptor(Application app) {
        return new HostSelectionInterceptor((HungryApplication) app);
    }

    @Provides
    @Named("host_enforced")
    @Singleton
    OkHttpClient provideOkHttpClient(HostSelectionInterceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(CONNECT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT_SECONDS, TimeUnit.SECONDS).build();
    }

    @Provides
    @Named("host_not_enforced")
    @Singleton
    OkHttpClient provideOkHttpClient2() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT_SECONDS, TimeUnit.SECONDS).build();
    }


    @Singleton
    @Provides
    FoodService provideFoodService(@Named("host_enforced")OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVER_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(FoodService.class);
    }

    @Singleton
    @Provides
    Picasso providePicasso(@Named("host_not_enforced")OkHttpClient okHttpClient) {
        return new Picasso.Builder(HungryApplication.getAppContext())
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new ViewModelFactory(viewModelSubComponent.build());
    }
}
