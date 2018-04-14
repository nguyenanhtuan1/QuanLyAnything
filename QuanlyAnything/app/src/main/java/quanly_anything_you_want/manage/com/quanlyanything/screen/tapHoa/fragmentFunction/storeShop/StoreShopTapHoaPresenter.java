package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop;


import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;


public class StoreShopTapHoaPresenter extends BasePresenter implements StoreShopTapHoaContact.Presenter {

    List<ProductTapHoa> list;

    StoreShopTapHoaPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public StoreShopTapHoaContact.View getView() {
        return (StoreShopTapHoaContact.View) super.getView();
    }

}
