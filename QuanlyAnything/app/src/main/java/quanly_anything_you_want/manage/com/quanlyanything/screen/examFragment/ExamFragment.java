package quanly_anything_you_want.manage.com.quanlyanything.screen.examFragment;

import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;

public class ExamFragment extends BaseFragment implements ExamFragmentContact.View {
    ExamFragmentPresenter mPresenter;

    @Override
    protected void onInitData() {
        mPresenter = new ExamFragmentPresenter(this);

    }

    @Override
    protected void onInitListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_store_shop_tap_hoa;
    }
}
