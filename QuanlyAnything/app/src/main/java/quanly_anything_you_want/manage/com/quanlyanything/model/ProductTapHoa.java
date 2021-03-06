package quanly_anything_you_want.manage.com.quanlyanything.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop.adapter.ProductTapHoaSend;

public class ProductTapHoa extends RealmObject implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("creator")
    public String creator;

    @SerializedName("photo")
    public String photo;

    @SerializedName("pricePurchase")
    public double pricePurchase;//giá trị sản phẩm /1 đơn vị sản phảm

    @SerializedName("priceWholesale")
    public double priceWholesale;//giá bán sỉ

    @SerializedName("priceRetail")
    public double priceRetail;//giá bán lẻ

    @SerializedName("active")
    public boolean status;

    @SerializedName("unitProduct")
    public String unitProduct;

    @SerializedName("unitWholesale")
    public String unitWholesale;

    @SerializedName("unitRetail")
    public String unitRetail;

    @SerializedName("codeProduct")
    public String codeProduct;

    public boolean isDelete;

    public ProductTapHoa() {
        status = true;
    }

    public ProductTapHoa(ProductTapHoaSend data) {
        id = data.id;
        name = data.name;
        photo = data.photo;
        pricePurchase = data.pricePurchase;//giá trị sản phẩm /1 đơn vị sản phảm
        priceWholesale = data.priceWholesale;//giá bán sỉ
        priceRetail = data.priceRetail;//giá bán lẻ
        status = data.status;
        unitProduct = data.unitProduct;
        unitWholesale = data.unitWholesale;
        unitRetail = data.unitRetail;
        codeProduct = data.codeProduct;
    }

    public void convert(ProductTapHoa data) {
        name = data.name;
        photo = data.photo;
        pricePurchase = data.pricePurchase;//giá trị sản phẩm /1 đơn vị sản phảm
        priceWholesale = data.priceWholesale;//giá bán sỉ
        priceRetail = data.priceRetail;//giá bán lẻ
        status = data.status;
        unitProduct = data.unitProduct;
        unitWholesale = data.unitWholesale;
        unitRetail = data.unitRetail;
        codeProduct = data.codeProduct;
    }
}
