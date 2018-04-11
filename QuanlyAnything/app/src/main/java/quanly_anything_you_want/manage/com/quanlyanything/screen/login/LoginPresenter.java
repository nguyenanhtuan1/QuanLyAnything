package quanly_anything_you_want.manage.com.quanlyanything.screen.login;



import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;


public class LoginPresenter extends BasePresenter implements LoginContact.Presenter {

    @Override
    public void login(String email, String password, final String baseUrl) {
        if (!isViewAttached()) return;


        getView().showLoading();
    }

    @Override
    public LoginContact.View getView() {
        return (LoginContact.View) super.getView();
    }

}
