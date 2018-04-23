package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import;


import java.util.ArrayList;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProductDto;


public class ImportTapHoaFragmentPresenter extends BasePresenter implements ImportTapHoaFragmentContact.Presenter {
    private BillImportProductDto billImportProductDto;


    public BillImportProductDto getBillImportProductDto() {
        return billImportProductDto;
    }

    ImportTapHoaFragmentPresenter(IBaseView view) {
        super.onCreate(view);
        billImportProductDto = new BillImportProductDto();

    }

    @Override
    public ArrayList<String> getListNameProduct() {
        ArrayList<String> list = new ArrayList<>();
        for (ProductChooseDto item : billImportProductDto.getListProduct()) {
            list.add(item.name);
        }
        return list;
    }

    @Override
    public ImportTapHoaFragmentContact.View getView() {
        return (ImportTapHoaFragmentContact.View) super.getView();
    }

    @Override
    public void resetData() {
        billImportProductDto.nameSeller = "";
        billImportProductDto.getListProduct().clear();
        getView().updateData();
        getView().notifyAllDataAdapter();
    }

    public void completeImportProduct(String seller) {
        billImportProductDto.nameSeller = seller;
        resetData();
    }

    public void setRemoveProduct(int position) {
        billImportProductDto.getListProduct().remove(position);
        getView().updateData();
        getView().notifyRemoveDataAdapter(position);
    }
}
