package quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type;

import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProduct;

public class ReloadImportHistory {
    public BillImportProduct importProductDto;

    public ReloadImportHistory(BillImportProduct importProductDto) {
        this.importProductDto = importProductDto;
    }
}
