package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell;


import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;


public class SellTapHoaPresenter extends BasePresenter implements SellTapHoaContact.Presenter {
    SellTapHoaPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public SellTapHoaContact.View getView() {
        return (SellTapHoaContact.View) super.getView();
    }

}
