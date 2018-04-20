package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct;


import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;


public class ChooseProductPresenter extends BasePresenter implements ChooseProductContact.Presenter {

    private List<ProductChooseDto> listDisplay = new ArrayList<>();
    private List<ProductChooseDto> listStore = new ArrayList<>();

    ChooseProductPresenter(IBaseView view, ArrayList<String> nameProduct) {
        super.onCreate(view);

        for (ProductTapHoa item : getCachesManager().listProduct) {
            boolean isAdd = true;
            for (String id : nameProduct) {
                if (item.name.equalsIgnoreCase(id)) {
                    isAdd = false;
                    break;
                }
            }
            if (isAdd)
                listStore.add(new ProductChooseDto(item.name, item.codeProduct, item.priceWholesale, item.priceRetail, item.unitWholesale, item.unitRetail, item.currency));

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
    public ChooseProductContact.View getView() {
        return (ChooseProductContact.View) super.getView();
    }


}
