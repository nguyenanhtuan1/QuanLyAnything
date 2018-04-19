package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct;


import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;

public interface ChooseProductContact {

    interface View extends IBaseView {
        void onNotifyAdapterProduct();
    }

    interface Presenter {

        List<ProductChooseDto> getListDisplay();

        List<ProductChooseDto> getListSave();

        void onSearchProduct(String text);
    }

}
