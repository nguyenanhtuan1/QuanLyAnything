package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history;


import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type.ReloadImportHistory;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProductDto;

public class HistoryTapHoaPresenter extends BasePresenter implements HistoryTapHoaContact.Presenter {
    private List<BillImportProductDto> listImport;

    public List<BillImportProductDto> getListImport() {
        return listImport;
    }

    HistoryTapHoaPresenter(IBaseView view) {
        super.onCreate(view);
        getEventManager().register(this);
        listImport = new ArrayList<>();
        listImport.addAll(getCachesManager().getListBillImport());
    }

    @Override
    public HistoryTapHoaContact.View getView() {
        return (HistoryTapHoaContact.View) super.getView();
    }

    @Subscribe
    void onReloadBillImport(ReloadImportHistory data) {
        if (data.importProductDto != null) {
            listImport.add(data.importProductDto);
            getView().onNotifyAdapterImport();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getEventManager().unRegister(this);
    }
}
