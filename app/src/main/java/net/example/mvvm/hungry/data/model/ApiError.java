package net.example.mvvm.hungry.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class ApiError {
    private int code;
    private String message;

}
