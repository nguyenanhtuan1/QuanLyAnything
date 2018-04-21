package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import;


import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;


public class ImportTapHoaFragmentPresenter extends BasePresenter implements ImportTapHoaFragmentContact.Presenter {
    ImportTapHoaFragmentPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public ImportTapHoaFragmentContact.View getView() {
        return (ImportTapHoaFragmentContact.View) super.getView();
    }

}
