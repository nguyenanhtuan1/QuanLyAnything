package quanly_anything_you_want.manage.com.quanlyanything.screen.detail_product;

import java.util.Date;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;


public class DetailProductPresenter extends BasePresenter implements DetailProductContact.Presenter {
    DetailProductPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public DetailProductContact.View getView() {
        return (DetailProductContact.View) super.getView();
    }

    @Override
    public void onCreateProduct(ProductTapHoa product) {
        long id = new Date().getTime();
        product.id = String.valueOf(id);
        getCachesManager().addMoreProduct(product);
        getView().onCreateProductSuccess(product);
    }

    @Override
    public void onUpdateProduct(final ProductTapHoa product) {
        getCachesManager().updateProduct(product);
        getView().onCreateProductSuccess(product);
    }

    @Override
    public void onDeleteProduct(final ProductTapHoa product) {
        getCachesManager().deleteProduct(product);
        getView().onDeleteSuccess();
    }

}
