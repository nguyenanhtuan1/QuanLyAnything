package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct;


import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;


public class ChooseProductPresenter extends BasePresenter implements ChooseProductContact.Presenter {

    private List<ProductChooseDto> listDisplay = new ArrayList<>();
    private List<ProductChooseDto> listStore = new ArrayList<>();

    ChooseProductPresenter(IBaseView view) {
        super.onCreate(view);

        for (ProductTapHoa item : getCachesManager().listProduct) {
            listStore.add(new ProductChooseDto(item.name, item.codeProduct, item.priceWholesale, item.priceRetail, item.unitWholesale, item.unitRetail));

        }
        listDisplay.addAll(listStore);
    }

    @Override
    public List<ProductChooseDto> getListDisplay() {
        return listDisplay;
    }

    @Override
    public List<ProductChooseDto> getListSave() {
        List<ProductChooseDto> list = new ArrayList<>();
        for (ProductChooseDto item : listStore) {
            if (item.quantityWholesale != 0 || item.quantityRetail != 0) {
                list.add(item);
            }
        }
        return list;
    }

    @Override
    public void onSearchProduct(String text) {
        listDisplay.clear();
        for (int i = 0; i < listStore.size(); i++) {
            if (listStore.get(i).name.toLowerCase().contains(text.toLowerCase()) || listStore.get(i).codeProduct.toLowerCase().contains(text.toLowerCase())) {
                listDisplay.add(listStore.get(i));
            }
        }
        getView().onNotifyAdapterProduct();
    }


    @Override
    public ChooseProductContact.View getView() {
        return (ChooseProductContact.View) super.getView();
    }

}
