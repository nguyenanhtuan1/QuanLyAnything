package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history.adapter;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProduct;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

public class HistoryBillImport implements Serializable {
    public long id;

    public String nameSeller;

    private RealmList<ProductChooseDto> listProduct;

    public boolean isShowProduct;

    public HistoryBillImport(BillImportProduct data) {
        this.id = data.id;
        this.nameSeller = data.nameSeller;
        this.listProduct = new RealmList<>();
        this.listProduct.addAll(data.listProduct);
    }

    public HistoryBillImport() {

    }

    public List<ProductChooseDto> getListProduct() {
        if (listProduct == null) listProduct = new RealmList<>();
        return listProduct;
    }

    public String getNameTotalProduct() {
        StringBuilder text = new StringBuilder();
        for (ProductChooseDto item : getListProduct()) {
            if (item.quantityImport != 0) {
                text.append("- ");
                text.append(item.quantityImport);
                text.append(" ");
                text.append(item.unitImport);
                text.append(" ");
                text.append(item.name != null ? item.name : "");
                text.append(System.getProperty("line.separator"));
            }
        }
        if (text.length() > 0)
            text.deleteCharAt(text.length() - 1);
        return text.toString();
    }

    public String getTotalAmountProduct() {
        double amount = 0;
        for (ProductChooseDto item : getListProduct()) {
            amount = amount + (item.quantityImport * item.priceImport);
        }
        return CommonUtil.showPriceHasCurrency(amount);
    }

}
