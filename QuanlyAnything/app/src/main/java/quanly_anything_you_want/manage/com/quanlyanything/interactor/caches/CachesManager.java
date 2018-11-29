package quanly_anything_you_want.manage.com.quanlyanything.interactor.caches;


import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProduct;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.adapter.BillSellProduct;

public class CachesManager {

    public synchronized static CachesManager getInstance() {
        return INSTANCE;
    }

    private static final CachesManager INSTANCE = new CachesManager();
    Realm myRealm;
    private List<BillSellProduct> listBillSell;
    private List<UserContact> listUserContact;

    private CachesManager() {
        myRealm = Realm.getDefaultInstance();
        listBillSell = new ArrayList<>();
    }

    // start row to product
    public void addMoreProduct(ProductTapHoa product) {
        myRealm.beginTransaction();
        myRealm.copyToRealm(product);
        myRealm.commitTransaction();
    }

    public void updateProduct(ProductTapHoa product) {
        final RealmResults<ProductTapHoa> puppies = myRealm.where(ProductTapHoa.class).findAll();
        puppies.size();
        myRealm.executeTransactionAsync(bgRealm -> {
            ProductTapHoa data = bgRealm.where(ProductTapHoa.class).equalTo("id", product.id).findFirst();
            assert data != null;
            data.convert(product);
        });
    }

    public void deleteProduct(ProductTapHoa product) {
        final RealmResults<ProductTapHoa> puppies = myRealm.where(ProductTapHoa.class).findAll();
        puppies.size();
        myRealm.executeTransactionAsync(bgRealm -> {
            ProductTapHoa data = bgRealm.where(ProductTapHoa.class).equalTo("id", product.id).findFirst();
            assert data != null;
            data.deleteFromRealm();
        });
    }

    public List<ProductTapHoa> getListProduct() {
        RealmResults<ProductTapHoa> results1 =
                myRealm.where(ProductTapHoa.class).findAll();
        if (results1 != null)
            return results1;
        return new ArrayList<>();
    }

    // end row to product
    public List<BillImportProduct> getListBillImport() {
        RealmResults<BillImportProduct> results1 =
                myRealm.where(BillImportProduct.class).findAll();
        if (results1 != null)
            return results1;
        return new ArrayList<>();
    }

    public void addMoreBIllImport(BillImportProduct billImportProduct) {
        myRealm.beginTransaction();
        myRealm.copyToRealm(billImportProduct);
        myRealm.commitTransaction();
    }

    public List<UserContact> getListUserContact() {
        return listUserContact;
    }


    public List<BillSellProduct> getListBillSell() {
        return listBillSell;
    }

    public void removeBillImportProduct(int position) {
    }

    public void removeBillSellProduct(int position) {
        listBillSell.remove(position);
    }


}
