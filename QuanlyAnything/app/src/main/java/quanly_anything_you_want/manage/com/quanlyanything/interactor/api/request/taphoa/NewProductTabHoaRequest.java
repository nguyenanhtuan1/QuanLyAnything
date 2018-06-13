package quanly_anything_you_want.manage.com.quanlyanything.interactor.api.request.taphoa;

import com.google.gson.annotations.SerializedName;

import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;

public class NewProductTabHoaRequest {
    @SerializedName("name")
    public String name;

    @SerializedName("creator")
    public String creator;

    @SerializedName("photo")
    public String photo;

    @SerializedName("currency")
    public String currency;

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

    public NewProductTabHoaRequest(ProductTapHoa data) {
        this.name = data.name;
        this.creator = data.creator;
        this.photo = data.photo;
        this.currency = data.currency;
        this.pricePurchase = data.pricePurchase;
        this.priceWholesale = data.priceWholesale;
        this.priceRetail = data.priceRetail;
        this.status = data.status;
        this.unitProduct = data.unitProduct;
        this.unitWholesale = data.unitWholesale;
        this.unitRetail = data.unitRetail;
        this.codeProduct = data.codeProduct;
    }
}
