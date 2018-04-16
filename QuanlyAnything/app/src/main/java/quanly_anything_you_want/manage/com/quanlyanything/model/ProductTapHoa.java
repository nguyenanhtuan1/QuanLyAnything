package quanly_anything_you_want.manage.com.quanlyanything.model;

public class ProductTapHoa {
    public String name;
    public String photo;
    public String currency;
    public int totalQuantity;//tổng số lượng sản phẩm
    public double totalPrice;//tổng giá trị sản phẩm
    public double priceWholesale;//giá bán sỉ
    public double priceRetail;//giá bán lẻ
    public boolean status;
    public String unitTotalQuantity;
    public String unitWholesale;
    public String unitRetail;
    public String codeProduct;

    public ProductTapHoa() {
        this.currency = "VND";
        status = true;
    }
}
