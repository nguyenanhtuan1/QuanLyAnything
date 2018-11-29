package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;

public class ProductChooseDto extends RealmObject implements Serializable {
    public String id;
    public String name;
    public String photo;
    public double priceWholesale;//giá bán sỉ
    public double priceRetail;//giá bán lẻ
    public String unitWholesale;
    public String unitRetail;
    public String unitProduct;
    public String codeProduct;
    public int quantityWholesale;// so luong add product cho màn hình choose product
    public int quantityRetail;// so luong add product cho màn hình choose product
    public int quantityImport;
    public double priceImport;
    public String unitImport;

    public ProductChooseDto(){
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductChooseDto(ProductTapHoa data) {
        this.name = data.name;
        this.id = data.id;
        this.photo = data.photo;
        this.codeProduct = data.codeProduct;
        this.priceWholesale = data.priceWholesale;
        this.priceRetail = data.priceRetail;
        this.unitWholesale = data.unitWholesale;
        this.unitRetail = data.unitRetail;
        quantityWholesale = 0;
        quantityRetail = 0;
        this.unitProduct = data.unitProduct;
    }

    public ProductChooseDto(ProductChooseDto data) {
        this.name = data.name;
        this.photo = data.photo;
        this.id = data.id;
        this.priceWholesale = data.priceWholesale;
        this.priceRetail = data.priceRetail;
        this.unitWholesale = data.unitWholesale;
        this.unitRetail = data.unitRetail;
        quantityWholesale = data.quantityWholesale;
        quantityRetail = data.quantityRetail;
    }
}
