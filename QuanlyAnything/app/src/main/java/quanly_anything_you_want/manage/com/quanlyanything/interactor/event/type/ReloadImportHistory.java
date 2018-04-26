package quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type;

import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProductDto;

public class ReloadImportHistory {
    public BillImportProductDto importProductDto;

    public ReloadImportHistory(BillImportProductDto importProductDto) {
        this.importProductDto = importProductDto;
    }
}
