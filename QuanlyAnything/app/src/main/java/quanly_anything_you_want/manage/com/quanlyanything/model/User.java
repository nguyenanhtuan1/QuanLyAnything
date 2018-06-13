package quanly_anything_you_want.manage.com.quanlyanything.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class User implements Serializable {
    @SerializedName("name")
    public String name;

    @SerializedName("password")
    public String password;

    @SerializedName("_id")
    public String _id;


}
