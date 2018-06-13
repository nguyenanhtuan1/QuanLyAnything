package quanly_anything_you_want.manage.com.quanlyanything.screen.login;


import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;

public interface LoginContact {

    interface View extends IBaseView {
        void loginSuccess();
    }

    interface Presenter {

        void login(String email, String password);

        void createAccount(String email, String password);
    }

}
