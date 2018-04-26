package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter;

import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.utils.AppConstants;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

public class BillImportProductDto {
    public String date;
    public String nameSeller;
    private List<ProductChooseDto> listProduct;

    public BillImportProductDto(BillImportProductDto data) {
        this.date = data.date;
        this.nameSeller = data.nameSeller;
        this.listProduct = new ArrayList<>();
        this.listProduct.addAll(data.listProduct);
    }

    public BillImportProductDto() {

    }

    public List<ProductChooseDto> getListProduct() {
        if (listProduct == null) listProduct = new ArrayList<>();
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
        return CommonUtil.showPriceHasCurrency(amount, AppConstants.CURRENCY_DEFAULT);
    }

}
