package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop;


import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;

public interface StoreShopTapHoaContact {

    interface View extends IBaseView {
        void setValueBarcode(String barcode);

        void onNotifyAdapterProduct();

        void onNotifyAdapterProductAtPosition(int position);
    }

    interface Presenter {

        void addItemProduct(ProductTapHoa product);

        void setUpdateChangeProduct(int position, ProductTapHoa product);

        void deleteProduct(int position);

        void onSearchProduct(String text);

        void onSaveCacheProduct();
    }

}
