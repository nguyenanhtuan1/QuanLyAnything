package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProductImport;


import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type.ReloadListProduct;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;


public class ChooseProductImportPresenter extends BasePresenter implements ChooseProductImportContact.Presenter {

    private List<ProductChooseDto> listDisplay = new ArrayList<>();
    private List<ProductChooseDto> listStore = new ArrayList<>();

    ChooseProductImportPresenter(IBaseView view, ArrayList<String> nameProduct) {
        super.onCreate(view);
        for (ProductTapHoa item : getCachesManager().getListProduct()) {
            boolean isAdd = true;

            if (nameProduct != null) {
                for (String id : nameProduct) {
                    if (item.name.equalsIgnoreCase(id)) {
                        isAdd = false;
                        break;
                    }
                }
            }
            if (isAdd)
                listStore.add(new ProductChooseDto(item));

        }
        listDisplay.addAll(listStore);
    }

    @Override
    public void addMoreProduct(ProductTapHoa productTapHoa) {
        ProductChooseDto item = new ProductChooseDto(productTapHoa);
        listStore.add(item);
        listDisplay.add(0, item);
        getCachesManager().getListProduct().add(productTapHoa);
        getEventManager().sendEvent(new ReloadListProduct(productTapHoa));
        getView().onNotifyInsertedAdapterProduct(0);
    }

    @Override
    public List<ProductChooseDto> getListDisplay() {
        return listDisplay;
    }

    @Override
    public List<ProductChooseDto> getListSave() {
        List<ProductChooseDto> list = new ArrayList<>();
        for (ProductChooseDto item : listStore) {
            if (item.quantityImport != 0 && item.priceImport != 0) {
                list.add(item);
            }
        }
        return list;
    }

    @Override
    public void onSearchProduct(String text) {
        listDisplay.clear();
        for (int i = 0; i < listStore.size(); i++) {
            String valueName = Normalizer.normalize(listStore.get(i).name != null ? listStore.get(i).name : "", Normalizer.Form.NFD);
            valueName = valueName.replaceAll("[^\\p{ASCII}]", "");

            String valueSearch = Normalizer.normalize(text, Normalizer.Form.NFD);
            valueSearch = valueSearch.replaceAll("[^\\p{ASCII}]", "");

            if (valueName.toLowerCase().contains(valueSearch.toLowerCase())
                    || (listStore.get(i).codeProduct != null && listStore.get(i).codeProduct.toLowerCase().contains(valueSearch.toLowerCase()))) {
                listDisplay.add(listStore.get(i));
            }
        }
        getView().onNotifyAdapterProduct();
    }


    @Override
    public ChooseProductImportContact.View getView() {
        return (ChooseProductImportContact.View) super.getView();
    }


}
