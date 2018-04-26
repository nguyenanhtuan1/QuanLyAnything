package quanly_anything_you_want.manage.com.quanlyanything.interactor.caches;


import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProductDto;

public class CachesManager {

    public synchronized static CachesManager getInstance() {
        return INSTANCE;
    }

    private static final CachesManager INSTANCE = new CachesManager();

    private static  List<ProductTapHoa> listProduct;
    private static  List<BillImportProductDto> listBillImport;

    private CachesManager() {
        listBillImport = new ArrayList<>();

        listProduct = new ArrayList<>();
        listProduct.add(new ProductTapHoa("Sữa ông thọ", 19000, 200000, 10000, "Thùng", "Thùng", "Hộp"));
        listProduct.add(new ProductTapHoa("Mì tôm hảo hảo", 200000, 210000, 6000, "Thùng", "Thùng", "Gói"));
        listProduct.add(new ProductTapHoa("Mì chua cay", 180000, 220000, 7000, "Thùng", "Thùng", "Gói"));
        listProduct.add(new ProductTapHoa("Bánh trứng", 130000, 0, 150000, "Hộp", "", "Hộp"));
        listProduct.add(new ProductTapHoa("Gạo nếp", 12000, 0, 17000, "Kg", "", "Kg"));
    }

    public static List<ProductTapHoa> getListProduct(){
        return listProduct;
    }
    public static List<BillImportProductDto> getListBillImport(){
        return listBillImport;
    }



}
