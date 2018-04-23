package quanly_anything_you_want.manage.com.quanlyanything.model;

import java.io.Serializable;

public class ProductTapHoa implements Serializable {
    public String name;
    public String photo;
    public String currency;
    public int totalQuantity;//tổng số lượng sản phẩm
    public double pricePurchase;//giá trị sản phẩm /1 đơn vị sản phảm
    public double priceWholesale;//giá bán sỉ
    public double priceRetail;//giá bán lẻ
    public boolean status;
    public String unitProduct;
    public String unitWholesale;
    public String unitRetail;
    public String codeProduct;

//    public int quantityWholesale;// so luong add product cho màn hình choose product
//    public int quantityRetail;// so luong add product cho màn hình choose product

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
