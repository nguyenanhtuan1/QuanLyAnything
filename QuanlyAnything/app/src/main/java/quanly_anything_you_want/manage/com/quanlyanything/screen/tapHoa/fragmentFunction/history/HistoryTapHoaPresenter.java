package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history;


import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type.ReloadImportHistory;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type.ReloadSellHistory;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProduct;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.adapter.BillSellProduct;

public class HistoryTapHoaPresenter extends BasePresenter implements HistoryTapHoaContact.Presenter {
    private List<BillImportProduct> listImport;
    private List<BillSellProduct> listSell;

    public List<BillImportProduct> getListImport() {
        return listImport;
    }

    public List<BillSellProduct> getListSell() {
        return listSell;
    }

    HistoryTapHoaPresenter(IBaseView view) {
        super.onCreate(view);
        getEventManager().register(this);
        listImport = new ArrayList<>();
        listImport.addAll(getCachesManager().getListBillImport());

        listSell = new ArrayList<>();
        listSell.addAll(getCachesManager().getListBillSell());
    }

    @Override
    public HistoryTapHoaContact.View getView() {
        return (HistoryTapHoaContact.View) super.getView();
    }

    @Subscribe
    void onReloadBillImport(ReloadImportHistory data) {
        if (data.importProductDto != null) {
            listImport.add(0,data.importProductDto);
            getView().onNotifyAdapterImport();
        }
    }

    @Subscribe
    void onReloadBillSell(ReloadSellHistory data) {
        if (data.sellProduct != null) {
            listSell.add(0,data.sellProduct);
            getView().onNotifyAdapterSell();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getEventManager().unRegister(this);
    }

    public void setDeleteImportHistory(int positionHeader) {
        listImport.remove(positionHeader);
        getCachesManager().removeBillImportProduct(positionHeader);
        getView().onNotifyAdapterImport();
    }

    public void setDeleteSellHistory(int positionHeader) {
        listSell.remove(positionHeader);
        getCachesManager().removeBillSellProduct(positionHeader);
        getView().onNotifyAdapterSell();
    }
}
