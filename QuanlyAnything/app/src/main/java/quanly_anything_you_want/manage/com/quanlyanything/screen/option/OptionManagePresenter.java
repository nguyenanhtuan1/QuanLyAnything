package quanly_anything_you_want.manage.com.quanlyanything.screen.option;


import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;


public class OptionManagePresenter extends BasePresenter implements OptionManageContact.Presenter {
    OptionManagePresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public OptionManageContact.View getView() {
        return (OptionManageContact.View) super.getView();
    }

    @Override
    public void resetUser(){
        getPreferManager().resetUser();
    }

}
