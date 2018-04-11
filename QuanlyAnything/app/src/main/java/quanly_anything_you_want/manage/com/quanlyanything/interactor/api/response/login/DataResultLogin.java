package quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.horical.gito.chat.model.User;

import java.io.Serializable;

/**
 * Created by thien on 9/6/17.
 */

public class DataResultLogin implements Serializable {
    @SerializedName("user")
    @Expose
    public User user;

    @SerializedName("token")
    @Expose
    public String token;
}
