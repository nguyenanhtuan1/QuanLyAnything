package quanly_anything_you_want.manage.com.quanlyanything.interactor.api;


import quanly_anything_you_want.manage.com.quanlyanything.MainApplication;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.ApiCallback;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.ApiServices;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.RestCallback;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.RestError;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.request.login.LoginRequest;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.request.taphoa.NewProductTabHoaRequest;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.BaseResponse;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.login.LoginResponse;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.taphoa.AllProductTapHoaResponse;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.taphoa.CreateProductTapHoaResponse;

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

    public void createAccount(final LoginRequest request, final ApiCallback<LoginResponse> callback) {
        mApiServices.creteAccount("application/json", request)
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

    public void createProductTapHoa(final NewProductTabHoaRequest request, final ApiCallback<CreateProductTapHoaResponse> callback) {
        mApiServices.creteProductTapHoa("application/json", request)
                .enqueue(new RestCallback<CreateProductTapHoaResponse>() {
                    @Override
                    public void success(CreateProductTapHoaResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }

                });
    }

    public void updateProductTapHoa(String idProduct, final NewProductTabHoaRequest request, final ApiCallback<CreateProductTapHoaResponse> callback) {
        mApiServices.updateProductTapHoa("application/json", idProduct, request)
                .enqueue(new RestCallback<CreateProductTapHoaResponse>() {
                    @Override
                    public void success(CreateProductTapHoaResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }

                });
    }

    public void deleteProductTapHoa(String idProduct, final ApiCallback<BaseResponse> callback) {
        mApiServices.deleteProductTapHoa("application/json", idProduct)
                .enqueue(new RestCallback<BaseResponse>() {
                    @Override
                    public void success(BaseResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }

                });
    }

    public void getAllProduct(final String userId, final ApiCallback<AllProductTapHoaResponse> callback) {
        mApiServices.getAllProduct("application/json", userId)
                .enqueue(new RestCallback<AllProductTapHoaResponse>() {
                    @Override
                    public void success(AllProductTapHoaResponse res) {
                        callback.success(res);
                    }

                    @Override
                    public void failure(RestError error) {
                        callback.failure(error);
                    }

                });
    }


}