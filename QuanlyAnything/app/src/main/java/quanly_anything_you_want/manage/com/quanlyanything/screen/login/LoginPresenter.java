package quanly_anything_you_want.manage.com.quanlyanything.screen.login;


import android.os.Handler;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;


public class LoginPresenter extends BasePresenter implements LoginContact.Presenter {
    LoginPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public void login(String email, String password) {
        getView().showLoading();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getView().hideLoading();
                getView().loginSuccess();
            }
        }, 500);
    }

    @Override
    public LoginContact.View getView() {
        return (LoginContact.View) super.getView();
    }

}
