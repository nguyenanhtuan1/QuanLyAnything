package quanly_anything_you_want.manage.com.quanlyanything.screen.detail_product;


import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;

public interface DetailProductContact {

    interface View extends IBaseView {
        void onCreateProductSuccess(ProductTapHoa product);

        void onDeleteSuccess();
    }

    interface Presenter {

        void onCreateProduct(ProductTapHoa product);

        void onUpdateProduct(ProductTapHoa product);

        void onDeleteProduct(ProductTapHoa product);
    }

}
