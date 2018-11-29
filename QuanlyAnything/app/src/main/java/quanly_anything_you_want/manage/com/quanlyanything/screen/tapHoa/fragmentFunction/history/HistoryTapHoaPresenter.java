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
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history.adapter.HistoryBillImport;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history.adapter.HistoryBillSellProduct;

public class HistoryTapHoaPresenter extends BasePresenter implements HistoryTapHoaContact.Presenter {
    private List<HistoryBillImport> listImport;
    private List<HistoryBillSellProduct> listSell;

    public List<HistoryBillImport> getListImport() {
        return listImport;
    }

    public List<HistoryBillSellProduct> getListSell() {
        return listSell;
    }

    HistoryTapHoaPresenter(IBaseView view) {
        super.onCreate(view);
        getEventManager().register(this);
        listImport = new ArrayList<>();
        for (BillImportProduct item : getCachesManager().getListBillImport()) {
            listImport.add(new HistoryBillImport(item));
        }

        listSell = new ArrayList<>();
        for (BillSellProduct item : getCachesManager().getListBillSell()) {
            listSell.add(new HistoryBillSellProduct(item));
        }
    }

    @Override
    public HistoryTapHoaContact.View getView() {
        return (HistoryTapHoaContact.View) super.getView();
    }

    @Subscribe
    void onReloadBillImport(ReloadImportHistory data) {
        if (data.importProductDto != null) {
            listImport.add(0, new HistoryBillImport(data.importProductDto));
            getView().onNotifyAdapterImport();
        }
    }

    @Subscribe
    void onReloadBillSell(ReloadSellHistory data) {
        if (data.sellProduct != null) {
            listSell.add(0, new HistoryBillSellProduct(data.sellProduct));
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
        getCachesManager().removeBillImportProduct(listImport.get(positionHeader).id);
        getView().onNotifyAdapterImport();
    }

    public void setDeleteSellHistory(int positionHeader) {
        listSell.remove(positionHeader);
        getCachesManager().removeBillSellProduct(listSell.get(positionHeader).id);
        getView().onNotifyAdapterSell();
    }
}
