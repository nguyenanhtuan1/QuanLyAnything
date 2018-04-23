package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import;


import java.util.ArrayList;

import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;

public interface ImportTapHoaFragmentContact {

    interface View extends IBaseView {
        void updateData();

        void notifyAllDataAdapter();

        void notifyRemoveDataAdapter(int position);
    }

    interface Presenter {

        ArrayList<String> getListNameProduct();

        void resetData();
    }

}
