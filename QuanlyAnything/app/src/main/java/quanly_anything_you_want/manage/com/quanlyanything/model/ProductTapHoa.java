package quanly_anything_you_want.manage.com.quanlyanything.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductTapHoa implements Serializable {
    @SerializedName("name")
    public String name;

    @SerializedName("_id")
    public String _id;

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

    public boolean isDelete;

    public ProductTapHoa() {
        this.currency = "VND";
        status = true;
    }

    public ProductTapHoa(String name, double pricePurchase, double priceWholesale, double priceRetail, String unitProduct, String unitWholesale, String unitRetail) {
        this.name = name;
        this.pricePurchase = pricePurchase;
        this.priceWholesale = priceWholesale;
        this.priceRetail = priceRetail;
        this.unitProduct = unitProduct;
        this.unitWholesale = unitWholesale;
        this.unitRetail = unitRetail;
        this.currency = "VND";
        status = true;
    }
}
