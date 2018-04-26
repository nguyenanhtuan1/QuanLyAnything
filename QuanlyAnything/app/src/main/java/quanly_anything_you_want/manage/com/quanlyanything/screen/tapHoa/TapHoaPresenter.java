package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa;


import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;


public class TapHoaPresenter extends BasePresenter implements TapHoaContact.Presenter {

    public List<String> getListTabHeader() {
        List<String> list = new ArrayList<>();
        list.add("Kho hàng");
        list.add("Nhập hàng");
        list.add("Bán hàng");
        list.add("Báo cáo");
        list.add("Lịch sử");
        list.add("Danh bạ");
        return list;
    }

    TapHoaPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public TapHoaContact.View getView() {
        return (TapHoaContact.View) super.getView();
    }

}
