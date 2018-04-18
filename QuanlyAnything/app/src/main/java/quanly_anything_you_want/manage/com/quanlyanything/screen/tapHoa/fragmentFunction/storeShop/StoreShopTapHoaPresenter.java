package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;

public class StoreShopTapHoaPresenter extends BasePresenter implements StoreShopTapHoaContact.Presenter {
    private List<ProductTapHoa> listDisplay = new ArrayList<>();
    private List<ProductTapHoa> listStore = new ArrayList<>();
    String textSearch = "";

    List<ProductTapHoa> getListProduct() {
        return listDisplay;
    }

    StoreShopTapHoaPresenter(IBaseView view) {
        super.onCreate(view);

        listStore.addAll(getCachesManager().listProduct);
        listDisplay.addAll(listStore);
        Collections.sort(listDisplay, new CustomComparator());
    }

    @Override
    public void addItemProduct(ProductTapHoa product) {
        listStore.add(product);
        onSearchProduct(textSearch);
        getView().onNotifyAdapterProduct();
        onSaveCacheProduct();
    }

    @Override
    public void setUpdateChangeProduct(int position, ProductTapHoa product) {
        listDisplay.set(position, product);
        getView().onNotifyAdapterProductAtPosition(position);
        onSaveCacheProduct();
    }

    @Override
    public void addMoreQuantityProduct(int position, int quantity, double price, String seller) {
        ProductTapHoa item = listDisplay.get(position);
        item.totalQuantity = item.totalQuantity + quantity;
        item.totalPrice = item.totalPrice + price;
        getView().onNotifyAdapterProductAtPosition(position);
    }

    public class CustomComparator implements Comparator<ProductTapHoa> {
        @Override
        public int compare(ProductTapHoa o1, ProductTapHoa o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    @Override
    public void onSearchProduct(String text) {
        textSearch = text;
        listDisplay.clear();
        for (int i = 0; i < listStore.size(); i++) {
            if (listStore.get(i).name.toLowerCase().contains(text.toLowerCase())) {
                listDisplay.add(listStore.get(i));
            }
        }
        getView().onNotifyAdapterProduct();
    }

    @Override
    public StoreShopTapHoaContact.View getView() {
        return (StoreShopTapHoaContact.View) super.getView();
    }

    @Override
    public void onSaveCacheProduct() {
        getCachesManager().listProduct.clear();
        getCachesManager().listProduct.addAll(listStore);
    }

}
