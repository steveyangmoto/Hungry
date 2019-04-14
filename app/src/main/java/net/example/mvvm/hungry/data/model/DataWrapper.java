package net.example.mvvm.hungry.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class DataWrapper<T> {
    private ApiError apiError;
    private T data;
}