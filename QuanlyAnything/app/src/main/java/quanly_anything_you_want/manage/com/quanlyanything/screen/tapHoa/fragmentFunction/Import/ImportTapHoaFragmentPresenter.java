package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import;


import java.util.ArrayList;
import java.util.Calendar;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type.ReloadImportHistory;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProductDto;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;
import quanly_anything_you_want.manage.com.quanlyanything.utils.DateUtils;


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

    @Override
    public void completeImportProduct(String seller) {
        billImportProductDto.nameSeller = seller;
        billImportProductDto.date = DateUtils.formatFullDateVN(Calendar.getInstance().getTime());
        getCachesManager().getListBillImport().add(new BillImportProductDto(billImportProductDto));
        getEventManager().sendEvent(new ReloadImportHistory(new BillImportProductDto(billImportProductDto)));
        resetData();
    }

    @Override
    public void setRemoveProduct(int position) {
        billImportProductDto.getListProduct().remove(position);
        getView().updateData();
        getView().notifyRemoveDataAdapter(position);
    }
}
