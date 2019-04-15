package net.example.mvvm.hungry.ui.restaurant;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.example.mvvm.hungry.BR;
import net.example.mvvm.hungry.R;
import net.example.mvvm.hungry.data.model.DataWrapper;
import net.example.mvvm.hungry.data.model.Restaurant;
import net.example.mvvm.hungry.databinding.ActivityRestaurantBinding;
import net.example.mvvm.hungry.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;

public class RestaurantActivity extends BaseActivity<ActivityRestaurantBinding,RestaurantViewModel> {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @Override
    public int getLayoutId() {
        return R.layout.activity_restaurant;
    }
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public RestaurantViewModel getViewModel() {
        RestaurantViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(RestaurantViewModel.class);
        return viewModel;
    }
    @Inject
    public RestaurantActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observeViewStates();
    }


    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }

    private void observeViewStates(){
        mViewModel.getRestaurantViewStateObservable().observe(this, new Observer<RestaurantViewModel.RestaurantViewState>() {
            @Override
            public void onChanged(@Nullable RestaurantViewModel.RestaurantViewState restaurantViewState) {
                if(restaurantViewState == RestaurantViewModel.RestaurantViewState.PENDING_LOAD_RESTAURANTS){
                    observeRestaurantData();
                }else if(restaurantViewState == RestaurantViewModel.RestaurantViewState.PENDING_LISTEN_TO_SCROLL_POSITION){
                    mViewDataBinding.restaurantList.setOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                            int offset = recyclerView.computeVerticalScrollOffset();
                            int extent = recyclerView.computeVerticalScrollExtent();
                            int range = recyclerView.computeVerticalScrollRange();
                            mViewModel.handleScrollChange(offset,range,extent);
                        }
                    });
                }else if(restaurantViewState == RestaurantViewModel.RestaurantViewState.PENDING_SHOW_PROGRESS_BAR){
                    showProgressBar(true);
                }else if(restaurantViewState == RestaurantViewModel.RestaurantViewState.PENDING_HIDE_PROGRESS_BAR){
                    showProgressBar(false);
                }
            }
        });
    }
    private void observeRestaurantData(){
        mViewModel.getRestaurantsObservable().observe(this, new Observer<DataWrapper<List<Restaurant>>>() {
            @Override
            public void onChanged(@Nullable DataWrapper<List<Restaurant>> listDataWrapper) {
                mViewModel.handleRestaurantListResponse(listDataWrapper);
            }
        });
    }

    private void showProgressBar(boolean show){
        if(show){
            mViewDataBinding.progressBar.setVisibility(View.VISIBLE);
        }else{
            mViewDataBinding.progressBar.setVisibility(View.GONE);
        }
    }
}
