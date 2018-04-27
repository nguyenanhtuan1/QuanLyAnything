package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.contact;


import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;


public class ContactTabHoaFragmentPresenter extends BasePresenter implements ContactTabHoaFragmentContact.Presenter {

    private List<UserContact> contactList = new ArrayList<>();

    public List<UserContact> getContactList() {
        return contactList;
    }

    ContactTabHoaFragmentPresenter(IBaseView view) {
        super.onCreate(view);

        contactList.add(new UserContact("Nguyễn trung vinh","0987234222",null,"Vinh bán bún"));
        contactList.add(new UserContact("Nguyễn Anh Tuấn","0987234222",null,"tuấn bán hành"));
        contactList.add(new UserContact("Nguyễn văn anh","0987234222",null,"anh bán kem"));
        contactList.add(new UserContact("Trần trung Huấn","0987234222",null,"Huấn bán gạo"));
        contactList.add(new UserContact("Hà văn tú","0987234222",null,"Tú bán kẹo dừa"));
//        if (getCachesManager().getListUserContact() != null)
//            contactList.addAll(getCachesManager().getListUserContact());
    }

    @Override
    public ContactTabHoaFragmentContact.View getView() {
        return (ContactTabHoaFragmentContact.View) super.getView();
    }

}
