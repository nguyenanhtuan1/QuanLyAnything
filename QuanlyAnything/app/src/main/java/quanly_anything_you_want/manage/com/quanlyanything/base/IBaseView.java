package quanly_anything_you_want.manage.com.quanlyanything.base;


import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.RestError;

public interface IBaseView {

    void showLoading();

    void hideLoading();

    void onFail(RestError error);

    void showErrorNormal(String error);

}
