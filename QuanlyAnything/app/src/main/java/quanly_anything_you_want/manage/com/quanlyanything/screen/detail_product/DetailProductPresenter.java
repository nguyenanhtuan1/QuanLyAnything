package quanly_anything_you_want.manage.com.quanlyanything.screen.detail_product;


import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;


public class DetailProductPresenter extends BasePresenter implements DetailProductContact.Presenter {
    DetailProductPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public DetailProductContact.View getView() {
        return (DetailProductContact.View) super.getView();
    }

}
