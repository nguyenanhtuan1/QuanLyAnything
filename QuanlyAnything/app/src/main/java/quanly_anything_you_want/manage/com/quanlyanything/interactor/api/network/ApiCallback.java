package quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network;


import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.BaseResponse;

public abstract class ApiCallback<T extends BaseResponse> {

    public abstract void success(T res);

    public abstract void failure(RestError error);

}