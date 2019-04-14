package net.example.mvvm.hungry.data.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static net.example.mvvm.hungry.Constants.GENERIC_EXCEPTION_MESSAGE;

abstract public class ApiCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            handleResponseData(response.body());
        } else {
            handleError(response);
        }
    }

    abstract protected void handleResponseData(T responseObject);

    abstract protected void handleError(Response<T> errorResponse);

    abstract protected void handleException(Exception t);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof Exception) {
            handleException((Exception) t);
        }else{
            handleException(new Exception(GENERIC_EXCEPTION_MESSAGE));
        }
    }
}
