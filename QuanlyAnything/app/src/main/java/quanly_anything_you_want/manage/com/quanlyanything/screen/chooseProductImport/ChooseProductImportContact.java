package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProductImport;


import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;

public interface ChooseProductImportContact {

    interface View extends IBaseView {
        void onNotifyAdapterProduct();

        void onNotifyInsertedAdapterProduct(int position);
    }

    interface Presenter {

        void addMoreProduct(ProductTapHoa productTapHoa);

        List<ProductChooseDto> getListDisplay();

        List<ProductChooseDto> getListSave();

        void onSearchProduct(String text);
    }

}
