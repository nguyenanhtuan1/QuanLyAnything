package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type.ReloadImportHistory;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProduct;
import quanly_anything_you_want.manage.com.quanlyanything.utils.DateUtils;


public class ImportTapHoaFragmentPresenter extends BasePresenter implements ImportTapHoaFragmentContact.Presenter {
    private BillImportProduct billImportProduct;


    public BillImportProduct getBillImportProduct() {
        return billImportProduct;
    }

    ImportTapHoaFragmentPresenter(IBaseView view) {
        super.onCreate(view);
        billImportProduct = new BillImportProduct();

    }

    @Override
    public ArrayList<String> getListNameProduct() {
        ArrayList<String> list = new ArrayList<>();
        for (ProductChooseDto item : billImportProduct.getListProduct()) {
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
        billImportProduct.nameSeller = "";
        billImportProduct.getListProduct().clear();
        getView().updateData();
        getView().notifyAllDataAdapter();
    }

    @Override
    public void completeImportProduct(String seller) {
        billImportProduct.nameSeller = seller;
        billImportProduct.id = new Date().getTime();
        getCachesManager().addMoreBIllImport(new BillImportProduct(billImportProduct));
        getEventManager().sendEvent(new ReloadImportHistory(new BillImportProduct(billImportProduct)));
        resetData();
    }

    @Override
    public void setRemoveProduct(int position) {
        billImportProduct.getListProduct().remove(position);
        getView().updateData();
        getView().notifyRemoveDataAdapter(position);
    }
}
