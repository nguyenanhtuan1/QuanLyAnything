package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.contact;


import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;


public class ContactTabHoaFragmentPresenter extends BasePresenter implements ContactTabHoaFragmentContact.Presenter {
    ContactTabHoaFragmentPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public ContactTabHoaFragmentContact.View getView() {
        return (ContactTabHoaFragmentContact.View) super.getView();
    }

}
