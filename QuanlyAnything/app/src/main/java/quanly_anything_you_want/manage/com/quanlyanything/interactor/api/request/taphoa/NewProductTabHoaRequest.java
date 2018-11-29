package quanly_anything_you_want.manage.com.quanlyanything.interactor.api.request.taphoa;

import com.google.gson.annotations.SerializedName;

import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;

public class NewProductTabHoaRequest {
    @SerializedName("name")
    public String name;

    @SerializedName("creator")
    private String creator;

    @SerializedName("photo")
    private String photo;

    @SerializedName("currency")
    public String currency;

    @SerializedName("pricePurchase")
    private double pricePurchase;//giá trị sản phẩm /1 đơn vị sản phảm

    @SerializedName("priceWholesale")
    private double priceWholesale;//giá bán sỉ

    @SerializedName("priceRetail")
    private double priceRetail;//giá bán lẻ

    @SerializedName("active")
    private boolean status;

    @SerializedName("unitProduct")
    private String unitProduct;

    @SerializedName("unitWholesale")
    private String unitWholesale;

    @SerializedName("unitRetail")
    private String unitRetail;

    @SerializedName("codeProduct")
    private String codeProduct;

    public NewProductTabHoaRequest(ProductTapHoa data) {
        this.name = data.name;
        this.creator = data.creator;
        this.photo = data.photo;
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
