package quanly_anything_you_want.manage.com.quanlyanything.interactor.api;


import android.app.Application;

import com.google.gson.GsonBuilder;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.ApiCallback;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.ApiServices;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.HeadersInterceptor;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.RestCallback;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.RestError;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.request.login.LoginRequest;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.login.LoginResponse;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.event.EventManager;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.prefer.PreferManager;
import quanly_anything_you_want.manage.com.quanlyanything.utils.AppConstants;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiManager {

    private ApiServices mApiServices;
    private PreferManager mPreferManager;
    private EventManager mEventManager;

    public synchronized static final ApiManager getInstance() {
        return INSTANCE;
    }

    private static final ApiManager INSTANCE = new ApiManager();

    public ApiManager() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        builder.addInterceptor(new HeadersInterceptor());
        builder.cache(new Cache(new Application().getCacheDir(), cacheSize));
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);

        mApiServices = new Retrofit.Builder()
                .baseUrl(AppConstants.URL_APP)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .client(builder.build())
                .build().create(ApiServices.class);
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