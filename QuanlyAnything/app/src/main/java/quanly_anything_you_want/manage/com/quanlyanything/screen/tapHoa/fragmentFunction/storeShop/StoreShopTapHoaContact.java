package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop;


import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;

public interface StoreShopTapHoaContact {

    interface View extends IBaseView {
        void onNotifyAdapterProduct();
    }

    interface Presenter {

        void onSearchProduct(String text);
    }

}
