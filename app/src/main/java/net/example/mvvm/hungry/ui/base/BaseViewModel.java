package net.example.mvvm.hungry.ui.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import net.example.mvvm.hungry.HungryApplication;

import java.lang.reflect.Constructor;

public class BaseViewModel extends AndroidViewModel {
    protected final HungryApplication application;

    public BaseViewModel(@NonNull HungryApplication application){
        super(application);
        this.application = application;
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final HungryApplication application;

        public Factory(@NonNull HungryApplication application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            try {
                Constructor<?> ctor = modelClass.getConstructor(HungryApplication.class);
                Object object = ctor.newInstance(new Object[]{application});
                return (T) object;
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
    }
}
