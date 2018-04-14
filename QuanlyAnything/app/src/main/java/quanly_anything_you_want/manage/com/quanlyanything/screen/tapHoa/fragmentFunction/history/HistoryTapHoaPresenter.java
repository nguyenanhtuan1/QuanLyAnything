package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history;


import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;


public class HistoryTapHoaPresenter extends BasePresenter implements HistoryTapHoaContact.Presenter {
    HistoryTapHoaPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public HistoryTapHoaContact.View getView() {
        return (HistoryTapHoaContact.View) super.getView();
    }

}
