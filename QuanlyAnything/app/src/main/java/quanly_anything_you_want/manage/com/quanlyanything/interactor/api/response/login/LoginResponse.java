package quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.BaseResponse;

public class LoginResponse extends BaseResponse {

    @SerializedName("results")
    @Expose
    public DataResultLogin results;

}