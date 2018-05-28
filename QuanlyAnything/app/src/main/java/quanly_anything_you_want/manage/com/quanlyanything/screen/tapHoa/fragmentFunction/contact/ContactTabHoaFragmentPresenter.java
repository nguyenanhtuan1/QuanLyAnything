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
        if (getCachesManager().getListUserContact() != null)
            contactList.addAll(getCachesManager().getListUserContact());
    }

    @Override
    public ContactTabHoaFragmentContact.View getView() {
        return (ContactTabHoaFragmentContact.View) super.getView();
    }

    @Override
    public ArrayList<String> getListNumberPhone() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (UserContact item : contactList) {
            arrayList.add(item.phone);
        }
        return arrayList;
    }

    @Override
    public void setOnDeleteContact(int position) {
        contactList.remove(position);
        getView().onNotifyAdapterRemoveContactAtPosition(position);
    }

    @Override
    public void addMoreContact(UserContact value, int position) {
        contactList.add(0, value);
        getView().onNotifyAdapterInsertedContactAtPosition(position);
    }

    @Override
    public void addMoreContact(List<UserContact> value) {
        contactList.addAll(value);
        getView().onNotifyAdapterContact();
    }

    @Override
    public void editContact(UserContact value, int position) {
        getView().onNotifyAdapterContactAtPosition(position);
    }
}
