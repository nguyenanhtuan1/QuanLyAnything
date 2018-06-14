package quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network;

import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.request.login.LoginRequest;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.request.taphoa.NewProductTabHoaRequest;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.BaseResponse;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.login.LoginResponse;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.taphoa.AllProductTapHoaResponse;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.taphoa.CreateProductTapHoaResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiServices {

    @POST("login")
    Call<LoginResponse> login(
            @Header("media-type") String mediaType,
            @Body LoginRequest loginRequest
    );

    @POST("register")
    Call<LoginResponse> creteAccount(
            @Header("media-type") String mediaType,
            @Body LoginRequest loginRequest
    );

    @POST("createProduct")
    Call<CreateProductTapHoaResponse> creteProductTapHoa(
            @Header("media-type") String mediaType,
            @Body NewProductTabHoaRequest loginRequest
    );

    @PUT("updateProduct/{idProduct}")
    Call<CreateProductTapHoaResponse> updateProductTapHoa(
            @Header("media-type") String mediaType,
            @Path("idProduct") String idProduct,
            @Body NewProductTabHoaRequest loginRequest
    );

    @DELETE("deleteProduct/{idProduct}")
    Call<BaseResponse> deleteProductTapHoa(
            @Header("media-type") String mediaType,
            @Path("idProduct") String idProduct
    );

    @GET("allProduct/{creator}")
    Call<AllProductTapHoaResponse> getAllProduct(
            @Header("media-type") String mediaType,
            @Path("creator") String userId
    );
}