package net.example.mvvm.hungry.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter @Setter
public class RestaurantReqParam {
    private double lat;
    private double lng;
    private int offset;
    private int limit;
}
