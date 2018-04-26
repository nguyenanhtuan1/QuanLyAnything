package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.contact;

import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;

public class ContactTapHoaFragment extends BaseFragment implements ContactTabHoaFragmentContact.View {
    ContactTabHoaFragmentPresenter mPresenter;

    @Override
    protected void onInitData() {
        mPresenter = new ContactTabHoaFragmentPresenter(this);

    }

    @Override
    protected void onInitListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_contact_shop_tap_hoa;
    }
}
