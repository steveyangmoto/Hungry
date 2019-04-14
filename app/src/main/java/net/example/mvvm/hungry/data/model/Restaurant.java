package net.example.mvvm.hungry.data.model;

import lombok.Getter;

@Getter
public class Restaurant {
    private long id;
    private String name;
    private String description;
    private String cover_img_url;
    private String status;
    private long delivery_fee;//in cents
}
