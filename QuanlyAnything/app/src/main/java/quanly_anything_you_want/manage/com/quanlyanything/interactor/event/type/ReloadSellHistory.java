package quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type;

import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProduct;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.adapter.BillSellProduct;

public class ReloadSellHistory {
    public BillSellProduct sellProduct;

    public ReloadSellHistory(BillSellProduct sellProduct) {
        this.sellProduct = sellProduct;
    }
}
