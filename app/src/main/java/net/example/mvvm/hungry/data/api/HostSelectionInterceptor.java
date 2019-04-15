package net.example.mvvm.hungry.data.api;

import android.util.Log;

import net.example.mvvm.hungry.HungryApplication;

import java.io.IOException;
import java.net.URL;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

public class HostSelectionInterceptor implements Interceptor {
    private HungryApplication myApp;

    public HostSelectionInterceptor(HungryApplication hungryApplication){
        this.myApp = hungryApplication;
    }

    @Override
    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String reqHost = request.url().host();
         String myHost = new URL(myApp.getBaseUrl()).getHost();
        if (!reqHost.equals(myHost)) {
            HttpUrl newUrl = request.url().newBuilder()
                    .host(myHost)
                    .build();
            request = request.newBuilder()
                    .url(newUrl)
                    .build();
        }
        return chain.proceed(request);
    }
}