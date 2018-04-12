package quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import quanly_anything_you_want.manage.com.quanlyanything.model.User;

public class DataResultLogin implements Serializable {
    @SerializedName("user")
    @Expose
    public User user;

    @SerializedName("token")
    @Expose
    public String token;
}
