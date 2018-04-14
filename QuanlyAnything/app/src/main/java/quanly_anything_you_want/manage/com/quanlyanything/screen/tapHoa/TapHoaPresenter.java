package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa;


import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;


public class TapHoaPresenter extends BasePresenter implements TapHoaContact.Presenter {
    TapHoaPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public TapHoaContact.View getView() {
        return (TapHoaContact.View) super.getView();
    }

}
