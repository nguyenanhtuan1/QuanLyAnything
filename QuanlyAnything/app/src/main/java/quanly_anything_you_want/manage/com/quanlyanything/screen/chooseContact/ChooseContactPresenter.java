package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseContact;


import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;


public class ChooseContactPresenter extends BasePresenter implements ChooseContactContact.Presenter {

    private List<UserContact> listDisplay = new ArrayList<>();
    private List<UserContact> listStore = new ArrayList<>();

    ChooseContactPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public void setListStore(List<UserContact> list, ArrayList<String> phone) {
        for (UserContact item : list) {
            boolean isAdd = true;
            if (phone != null)
                for (String number : phone) {
                    if (item.phone != null && item.phone.equalsIgnoreCase(number)) {
                        isAdd = false;
                        break;
                    }
                }
            if (isAdd)
                listStore.add(item);

        }

        listDisplay.addAll(listStore);
        Collections.sort(listDisplay, new CustomComparator());

        getView().onNotifyAdapterProduct();
    }

    public class CustomComparator implements Comparator<UserContact> {
        @Override
        public int compare(UserContact o1, UserContact o2) {
            String name1 = o1.name != null ? o1.name : "";
            String name2 = o2.name != null ? o2.name : "";
            return name1.compareTo(name2);
        }
    }

    @Override
    public List<UserContact> getListDisplay() {
        return listDisplay;
    }

    @Override
    public List<UserContact> getListSave() {
        List<UserContact> list = new ArrayList<>();
        for (UserContact item : listStore) {
            if (item.isSelect) {
                list.add(item);
            }
        }
        return list;
    }

    @Override
    public void onSearchProduct(String text) {
        listDisplay.clear();
        for (int i = 0; i < listStore.size(); i++) {
            String valueName = Normalizer.normalize(listStore.get(i).name != null ? listStore.get(i).name : "", Normalizer.Form.NFD);
            valueName = valueName.replaceAll("[^\\p{ASCII}]", "");

            String valueSearch = Normalizer.normalize(text, Normalizer.Form.NFD);
            valueSearch = valueSearch.replaceAll("[^\\p{ASCII}]", "");

            if (valueName.toLowerCase().contains(valueSearch.toLowerCase())
                    || (listStore.get(i).phone != null && listStore.get(i).phone.toLowerCase().contains(valueSearch.toLowerCase()))) {
                listDisplay.add(listStore.get(i));
            }
        }
        getView().onNotifyAdapterProduct();
    }


    @Override
    public ChooseContactContact.View getView() {
        return (ChooseContactContact.View) super.getView();
    }


}
