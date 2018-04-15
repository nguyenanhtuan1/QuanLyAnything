package quanly_anything_you_want.manage.com.quanlyanything.model;

public class ProductTapHoa {
    public String name;
    public String photo;
    public double priceImport;
    public double priceExport;
    public boolean status = true;
    public int quantityAll;
    public int quantityExport;
    public String unit;
    public String currency;
    public String codeProduct;

    public ProductTapHoa(String name, String photo, double priceExport, int quantityAll,int quantityExport, String unit, String currency) {
        this.name = name;
        this.photo = photo;
        this.priceExport = priceExport;
        this.quantityAll = quantityAll;
        this.quantityExport = quantityExport;
        this.unit = unit;
        this.currency = currency;
    }

}
