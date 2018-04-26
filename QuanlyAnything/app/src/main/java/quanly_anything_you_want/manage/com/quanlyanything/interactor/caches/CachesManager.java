package quanly_anything_you_want.manage.com.quanlyanything.interactor.caches;


import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProduct;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.adapter.BillSellProduct;

public class CachesManager {

    public synchronized static CachesManager getInstance() {
        return INSTANCE;
    }

    private static final CachesManager INSTANCE = new CachesManager();

    private List<ProductTapHoa> listProduct;
    private List<BillImportProduct> listBillImport;
    private List<BillSellProduct> listBillSell;

    private CachesManager() {
        listBillImport = new ArrayList<>();
        listBillSell = new ArrayList<>();

        listProduct = new ArrayList<>();
        listProduct.add(new ProductTapHoa("Sữa ông thọ", 19000, 200000, 10000, "Thùng", "Thùng", "Hộp"));
        listProduct.add(new ProductTapHoa("Mì tôm hảo hảo", 200000, 210000, 6000, "Thùng", "Thùng", "Gói"));
        listProduct.add(new ProductTapHoa("Mì chua cay", 180000, 220000, 7000, "Thùng", "Thùng", "Gói"));
        listProduct.add(new ProductTapHoa("Bánh trứng", 130000, 0, 150000, "Hộp", "", "Hộp"));
        listProduct.add(new ProductTapHoa("Gạo nếp", 12000, 0, 17000, "Kg", "", "Kg"));
    }

    public List<ProductTapHoa> getListProduct() {
        return listProduct;
    }

    public List<BillImportProduct> getListBillImport() {
        return listBillImport;
    }

    public List<BillSellProduct> getListBillSell() {
        return listBillSell;
    }

    public void removeBillImportProduct(int position) {
        listBillImport.remove(position);
    }
    public void removeBillSellProduct(int position) {
        listBillSell.remove(position);
    }


}
