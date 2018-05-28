package quanly_anything_you_want.manage.com.quanlyanything.interactor.caches;


import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;
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
    private List<UserContact> listUserContact;

    private CachesManager() {
        listBillImport = new ArrayList<>();
        listBillSell = new ArrayList<>();
        listProduct = new ArrayList<>();
    }

    public List<UserContact> getListUserContact() {
        return listUserContact;
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
