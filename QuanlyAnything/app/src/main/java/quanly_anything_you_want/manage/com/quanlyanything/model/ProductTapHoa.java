package quanly_anything_you_want.manage.com.quanlyanything.model;

public class ProductTapHoa {
    public String name;
    public String photo;
    public double priceImport;
    public double priceExport;
    public boolean status = true;
    public int quantity;
    public String unit;
    public String currency;

    public ProductTapHoa(String name, String photo, double priceExport, int quantity, String unit, String currency) {
        this.name = name;
        this.photo = photo;
        this.priceExport = priceExport;
        this.quantity = quantity;
        this.unit = unit;
        this.currency = currency;
    }
}
