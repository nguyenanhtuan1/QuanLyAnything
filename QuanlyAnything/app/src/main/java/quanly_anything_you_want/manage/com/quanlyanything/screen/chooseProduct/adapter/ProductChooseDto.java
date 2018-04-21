package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter;

import java.io.Serializable;

public class ProductChooseDto implements Serializable {
    public String name;
    public String photo;
    public double priceWholesale;//giá bán sỉ
    public double priceRetail;//giá bán lẻ
    public String unitWholesale;
    public String unitRetail;
    public String codeProduct;
    public int quantityWholesale;// so luong add product cho màn hình choose product
    public int quantityRetail;// so luong add product cho màn hình choose product
    public String currency;

    public ProductChooseDto(String name, String codeProduct, double priceWholesale, double priceRetail, String unitWholesale, String unitRetail, String currency) {
        this.name = name;
        this.codeProduct = codeProduct;
        this.priceWholesale = priceWholesale;
        this.priceRetail = priceRetail;
        this.unitWholesale = unitWholesale;
        this.unitRetail = unitRetail;
        quantityWholesale = 0;
        quantityRetail = 0;
        this.currency = currency;
    }

    public ProductChooseDto(ProductChooseDto data) {
        this.name = data.name;
        this.priceWholesale = data.priceWholesale;
        this.priceRetail = data.priceRetail;
        this.unitWholesale = data.unitWholesale;
        this.unitRetail = data.unitRetail;
        quantityWholesale = data.quantityWholesale;
        quantityRetail = data.quantityRetail;
    }
}