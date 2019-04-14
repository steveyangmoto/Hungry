package net.example.mvvm.hungry;

import android.app.Application;
import android.content.Context;
import android.webkit.URLUtil;

import net.example.mvvm.hungry.data.api.HungryApi;

public class HungryApplication extends Application {
    private static Context appContext;
    private static HungryApplication app;
    private HungryApi api;

    @Override
    public void onCreate(){
        super.onCreate();
        appContext = getApplicationContext();
        api = new HungryApi(Constants.SERVER_URL);
        app = this;
    }

    public HungryApi getApi(){
        return api;
    }

    public static HungryApplication getApplication(){
        return app;
    }

    public static Context getAppContext(){
        return appContext;
    }

    public boolean setBaseUrl(String url){
        if(URLUtil.isValidUrl(url)){
            this.api = new HungryApi(url);
            return true;
        }
        return false;
    }
}
