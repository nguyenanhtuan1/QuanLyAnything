package quanly_anything_you_want.manage.com.quanlyanything.interactor.api.request.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("password")
    @Expose
    private String password;

    public LoginRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
