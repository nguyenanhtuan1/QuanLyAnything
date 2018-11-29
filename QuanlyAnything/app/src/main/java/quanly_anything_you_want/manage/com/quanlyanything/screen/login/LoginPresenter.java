package quanly_anything_you_want.manage.com.quanlyanything.screen.login;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.ApiCallback;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.RestError;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.request.login.LoginRequest;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.login.LoginResponse;

public class LoginPresenter extends BasePresenter implements LoginContact.Presenter {
    LoginPresenter(IBaseView view) {
        super.onCreate(view);
        if (getPreferManager().getUser() != null)
            getView().loginSuccess();
    }

//    @Override
//    public void login(String email, String password) {
//        getView().showLoading();
//        getApiManager().loginOwner(new LoginRequest(email, password), new ApiCallback<LoginResponse>() {
//            @Override
//            public void success(LoginResponse res) {
//                getPreferManager().setUser(res.results);
//                getView().hideLoading();
//                getView().loginSuccess();
//            }
//
//            @Override
//            public void failure(RestError error) {
//                getView().hideLoading();
//                getView().onFail(error);
//            }
//        });
//    }

//    @Override
//    public void createAccount(String name, String password) {
//        getView().showLoading();
//        getApiManager().createAccount(new LoginRequest(name, password), new ApiCallback<LoginResponse>() {
//            @Override
//            public void success(LoginResponse res) {
//                getPreferManager().setUser(res.results);
//                getView().hideLoading();
//                getView().loginSuccess();
//            }
//
//            @Override
//            public void failure(RestError error) {
//                getView().hideLoading();
//                getView().onFail(error);
//            }
//        });
//    }

    @Override
    public LoginContact.View getView() {
        return (LoginContact.View) super.getView();
    }

}
