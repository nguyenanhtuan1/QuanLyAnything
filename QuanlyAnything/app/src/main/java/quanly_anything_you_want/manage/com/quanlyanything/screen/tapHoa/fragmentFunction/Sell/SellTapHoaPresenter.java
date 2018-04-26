package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell;


import android.os.Handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type.ReloadSellHistory;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.adapter.BillSellProduct;
import quanly_anything_you_want.manage.com.quanlyanything.utils.DateUtils;

public class SellTapHoaPresenter extends BasePresenter implements SellTapHoaContact.Presenter {
    private List<BillSellProduct> listBill = new ArrayList<>();

    @Override
    public List<BillSellProduct> getListBill() {
        return listBill;
    }

    @Override
    public ArrayList<String> getListNameProduct(int position) {
        ArrayList<String> list = new ArrayList<>();
        for (ProductChooseDto item : listBill.get(position).getListProduct()) {
            list.add(item.name);
        }
        return list;
    }

    @Override
    public void setCancelBill(int positionParent) {
        listBill.remove(positionParent);
        getView().onNotifyAdapterBillRemoveAtPosition(positionParent);

        if (listBill.isEmpty()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    listBill.add(new BillSellProduct());
                    getView().onNotifyAdapterBillInsertedAtPosition(0);
                }
            }, 300);

        }
    }

    @Override
    public void setCompleteBill(int positionParent) {
        BillSellProduct itemSave = listBill.get(positionParent);
        itemSave.date = DateUtils.formatFullDateVN(Calendar.getInstance().getTime());
        getCachesManager().getListBillSell().add(0, itemSave);
        getEventManager().sendEvent(new ReloadSellHistory(itemSave));

        listBill.remove(positionParent);
        getView().onNotifyAdapterBillRemoveAtPosition(positionParent);

        if (listBill.isEmpty()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    listBill.add(new BillSellProduct());
                    getView().onNotifyAdapterBillInsertedAtPosition(0);
                }
            }, 300);

        }
    }

    @Override
    public ProductChooseDto getProductSelected(int positionParent, int positionChild) {
        return listBill.get(positionParent).getListProduct().get(positionChild);
    }

    SellTapHoaPresenter(IBaseView view) {
        super.onCreate(view);
        listBill.add(new BillSellProduct());
    }

    @Override
    public void addMoreBillItem() {
        listBill.add(0, new BillSellProduct());
        getView().onNotifyAdapterBillInsertedAtPosition(0);
    }

    @Override
    public void setDeleteItemProduct(int positionParent, int positionChild) {
        listBill.get(positionParent).getListProduct().remove(positionChild);
        getView().onNotifyAdapterBillAtPosition(positionParent);
    }

    @Override
    public void setUpdateQuantityProduct(int positionParent, int positionChild, int quantityWholesale, int quantityRetail) {
        ProductChooseDto item = listBill.get(positionParent).getListProduct().get(positionChild);
        item.quantityWholesale = quantityWholesale;
        item.quantityRetail = quantityRetail;
        getView().onNotifyAdapterBillAtPosition(positionParent);
    }

    @Override
    public SellTapHoaContact.View getView() {
        return (SellTapHoaContact.View) super.getView();
    }
}
