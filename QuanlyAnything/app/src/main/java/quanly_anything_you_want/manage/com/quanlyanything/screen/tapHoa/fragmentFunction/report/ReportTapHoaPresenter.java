package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.report;


import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;


public class ReportTapHoaPresenter extends BasePresenter implements ReportTapHoaContact.Presenter {
    ReportTapHoaPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public ReportTapHoaContact.View getView() {
        return (ReportTapHoaContact.View) super.getView();
    }

}
