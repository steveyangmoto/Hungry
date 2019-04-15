package net.example.mvvm.hungry.di;

import dagger.Subcomponent;

@Subcomponent
public interface DaggerBindingComponent extends android.databinding.DataBindingComponent {
    @Subcomponent.Builder
    interface Builder {
        DaggerBindingComponent build();
    }
}