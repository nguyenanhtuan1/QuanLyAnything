package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.contact;


import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;

public interface ContactTabHoaFragmentContact {

    interface View extends IBaseView {
        void onNotifyAdapterContact();

        void onNotifyAdapterContactAtPosition(int position);

        void onNotifyAdapterInsertedContactAtPosition(int position);

        void onNotifyAdapterRemoveContactAtPosition(int position);
    }

    interface Presenter {

        ArrayList<String> getListNumberPhone();

        void setOnDeleteContact(int position);

        void addMoreContact(UserContact value, int position);

        void addMoreContact(List<UserContact> value);

        void editContact(UserContact value, int position);
    }

}
