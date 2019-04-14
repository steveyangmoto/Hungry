package net.example.mvvm.hungry.ui.restaurant.list;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.example.mvvm.hungry.HungryApplication;
import net.example.mvvm.hungry.R;
import net.example.mvvm.hungry.data.model.Restaurant;
import net.example.mvvm.hungry.databinding.RestaurantListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RestaurantClickListener mListener;
    private LayoutInflater mInflater;
    private List<Restaurant> restaurantList = new ArrayList<>();

    public RestaurantsAdapter(RestaurantClickListener listener) {
        this.mInflater = LayoutInflater.from(HungryApplication.getAppContext());
        this.mListener = listener;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RestaurantViewHolder) holder).bind(restaurantList.get(position));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RestaurantListItemBinding binding = DataBindingUtil.inflate(this.mInflater, R.layout.restaurant_list_item, parent, false);
        return new RestaurantViewHolder(mListener, binding);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
        this.notifyDataSetChanged();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        final RestaurantListItemBinding binding;
        final RestaurantClickListener listener;

        public RestaurantViewHolder(RestaurantClickListener listener, RestaurantListItemBinding binding) {
            super(binding.getRoot());
            this.listener = listener;
            this.binding = binding;
        }

        void bind(Restaurant restaurant) {
            RestaurantItemViewModel restaurantItemViewModel = new RestaurantItemViewModel(listener, restaurant);
            binding.setViewModel(restaurantItemViewModel);
            binding.executePendingBindings();
        }
    }
    public interface RestaurantClickListener {
        void onRestaurantClick(Restaurant restaurant);
    }
}
