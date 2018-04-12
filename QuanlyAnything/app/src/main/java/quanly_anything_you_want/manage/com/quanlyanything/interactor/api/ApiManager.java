package quanly_anything_you_want.manage.com.quanlyanything.interactor.api;


import android.app.Application;

import com.google.gson.GsonBuilder;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import quanly_anything_you_want.manage.com.quanlyanything.MainApplication;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.ApiCallback;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.ApiServices;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.HeadersInterceptor;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.RestCallback;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.RestError;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.request.login.LoginRequest;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.login.LoginResponse;

public class ApiManager {

    private ApiServices mApiServices;

    public synchronized static final ApiManager getInstance() {
        return INSTANCE;
    }

    private static final ApiManager INSTANCE = new ApiManager();

    private ApiManager() {
        mApiServices = MainApplication.retrofit.create(ApiServices.class);
    }


    // ==== Login Owner ==== //
    public void loginOwner(final LoginRequest request, final ApiCallback<LoginResponse> callback) {
        mApiServices.login("application/json", request)
                .enqueue(new RestCallback<LoginResponse>() {
                    @Override
                    public void success(LoginResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }

                });
    }


}