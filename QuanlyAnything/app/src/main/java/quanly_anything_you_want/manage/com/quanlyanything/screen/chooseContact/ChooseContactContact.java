package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseContact;


import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.User;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;

public interface ChooseContactContact {

    interface View extends IBaseView {
        void onNotifyAdapterProduct();
    }

    interface Presenter {

        void setListStore(List<UserContact> list,ArrayList<String> phone);

        List<UserContact> getListDisplay();

        List<UserContact> getListSave();

        void onSearchProduct(String text);
    }

}
