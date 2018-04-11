package quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network;

import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.request.login.LoginRequest;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.login.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServices {

    @POST("api/user/login")
    Call<LoginResponse> login(
            @Header("media-type") String mediaType,
            @Body LoginRequest loginRequest
    );
}