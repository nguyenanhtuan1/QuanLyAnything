package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell;


import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.adapter.BillSellProduct;

public interface SellTapHoaContact {

    interface View extends IBaseView {
        void onNotifyAllAdapterBill();

        void onNotifyAdapterBillAtPosition(int position);

        void onNotifyAdapterBillRemoveAtPosition(int position);

        void onNotifyAdapterBillInsertedAtPosition(int position);
    }

    interface Presenter {

        List<BillSellProduct> getListBill();

        ArrayList<String> getListNameProduct(int position);

        void setCompleteBill(int positionParent);

        ProductChooseDto getProductSelected(int positionParent, int positionChild);

        void addMoreBillItem();

        void setDeleteItemProduct(int positionParent, int positionChild);

        void setUpdateQuantityProduct(int positionParent, int positionChild, int quantityWholesale, int quantityRetail);

        void setCancelBill(int positionParent);
    }

}
