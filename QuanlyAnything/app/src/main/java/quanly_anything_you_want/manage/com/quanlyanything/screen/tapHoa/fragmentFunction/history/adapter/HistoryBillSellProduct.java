package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history.adapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProduct;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.adapter.BillSellProduct;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

public class HistoryBillSellProduct implements Serializable {
    @PrimaryKey
    public long id;
    String nameClient;
    private List<ProductChooseDto> listProduct;
    boolean isShowProduct;

    public HistoryBillSellProduct(BillSellProduct data) {
        this.id = data.id;
        this.nameClient = data.nameClient;
        this.listProduct = new RealmList<>();
        this.listProduct.addAll(data.listProduct);
    }

    String getNameTotalProduct() {
        StringBuilder text = new StringBuilder();
        for (ProductChooseDto item : getListProduct()) {

            if (item.quantityWholesale != 0 || item.quantityRetail != 0) {
                text.append("- ");
            }

            if (item.quantityWholesale != 0) {
                text.append(item.quantityWholesale);
                text.append(" ");
                text.append(item.unitWholesale);
                if (item.quantityRetail != 0) {
                    text.append(" + ");
                }
            }
            if (item.quantityRetail != 0) {
                text.append(item.quantityRetail);
                text.append(" ");
                text.append(item.unitRetail);
            }

            if (item.quantityWholesale != 0 || item.quantityRetail != 0) {
                text.append(" ");
                text.append(item.name != null ? item.name : "");
                text.append(System.getProperty("line.separator"));
            }
        }
        if (text.length() > 0)
            text.deleteCharAt(text.length() - 1);
        return text.toString();
    }

    String getTextTotalAmountProduct() {
        double amount = 0;
        for (ProductChooseDto item : getListProduct()) {
            amount = amount + (item.quantityWholesale * item.priceWholesale) + (item.quantityRetail * item.priceRetail);
        }
        return CommonUtil.showPriceHasCurrency(amount);
    }

    public List<ProductChooseDto> getListProduct() {
        if (listProduct == null) listProduct = new ArrayList<>();
        return listProduct;
    }
}
