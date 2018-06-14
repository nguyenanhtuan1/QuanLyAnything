package quanly_anything_you_want.manage.com.quanlyanything.screen.detail_product;


import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.ApiCallback;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.RestError;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.request.taphoa.NewProductTabHoaRequest;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.BaseResponse;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.response.taphoa.CreateProductTapHoaResponse;
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
        getView().showLoading();
        product.creator = getPreferManager().getUser()._id;
        getApiManager().createProductTapHoa(new NewProductTabHoaRequest(product), new ApiCallback<CreateProductTapHoaResponse>() {
            @Override
            public void success(CreateProductTapHoaResponse res) {
                getView().hideLoading();
                getView().onCreateProductSuccess(res.results);
            }

            @Override
            public void failure(RestError error) {
                getView().hideLoading();
                getView().onFail(error);
            }
        });
    }

    @Override
    public void onUpdateProduct(final ProductTapHoa product) {
        getView().showLoading();
        getApiManager().updateProductTapHoa(product._id, new NewProductTabHoaRequest(product), new ApiCallback<CreateProductTapHoaResponse>() {
            @Override
            public void success(CreateProductTapHoaResponse res) {
                getView().hideLoading();
                getView().onCreateProductSuccess(product);
            }

            @Override
            public void failure(RestError error) {
                getView().hideLoading();
                getView().onFail(error);
            }
        });
    }
    @Override
    public void onDeleteProduct(final ProductTapHoa product) {
        getView().showLoading();
        getApiManager().deleteProductTapHoa(product._id, new ApiCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse res) {
                getView().hideLoading();
                getView().onDeleteSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().hideLoading();
                getView().onFail(error);
            }
        });
    }

}
