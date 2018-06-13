package quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.taphoa;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.BaseResponse;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;

public class AllProductTapHoaResponse extends BaseResponse {

    @SerializedName("results")
    @Expose
    public List<ProductTapHoa> results;

}